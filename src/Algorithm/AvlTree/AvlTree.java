package Algorithm.AvlTree;

import java.util.HashMap;
import java.util.Map;

public class AvlTree {

	private final static String formatStr = "%-2d(h=%-1d,v=%s) ";

	private AvlTreeNode root;

	public AvlTree() {

	}

	public String getValue(int key) {
		if (root != null) {
			return root.getValue(key);
		}
		return null;
	}

	public boolean add(int key, String val) {
		if (root == null) {
			root = new AvlTreeNode(key, val);
		} else {
			try {
				root = root.addNode(key, val);
			} catch (SameKeyExistException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean remove(int key) {
		if (root != null) {
			try {
				root = root.removeNode(key);
				return true;
			} catch (NoSuchKeyExistException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public int height() {
		if (root != null) {
			return root.level;
		}
		return 0;
	}

	public int size() {
		if (root != null) {
			return root.size();
		}
		return 0;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (root != null) {
			int order = 1;
			int nodeNum = (int) Math.pow(2, root.level) - 1;
			Map<Integer, AvlTreeNode> entry = new HashMap<Integer, AvlTreeNode>();
			entry.put(order, root);
			for (int height = 1; order <= nodeNum; order++) {
				AvlTreeNode avlTreeNode = entry.get(order);
				if (avlTreeNode != null) {
					buffer.append(String.format(formatStr, avlTreeNode.key, avlTreeNode.level, avlTreeNode.val));
					entry.put(2 * order, avlTreeNode.left);
					entry.put(2 * order + 1, avlTreeNode.right);
				} else {
					buffer.append(String.format("%-12s", "&nbsp"));
				}
				if (order == ((int) Math.pow(2, height) - 1)) {
					height++;
					buffer.append("\n");
				}
			}
		}
		return buffer.toString();
	}

	public class AvlTreeNode {

		private int key;

		private String val;

		private int level = 1;

		private AvlTreeNode left;

		private AvlTreeNode right;

		private AvlTreeNode(int key, String val) {
			this.key = key;
			this.val = val;
		}

		private String getValue(int key) {
			if (key == this.key) {
				return val;
			}
			if (key < this.key) {
				if (left != null) {
					return left.getValue(key);
				}
			}
			if (key > this.key) {
				if (right != null) {
					return right.getValue(key);
				}
			}
			return null;
		}

		private AvlTreeNode addNode(int key, String val) throws SameKeyExistException {
			if (key < this.key) {
				if (left != null) {
					left = left.addNode(key, val);
					if (this.unbalanceRate() >= 2) {
						if (left.unbalanceRate() > 0) {
							return this.llRotate();
						}
						if (left.unbalanceRate() < 0) {
							return this.lrRotate();
						}
					}
					level = this.calLevel();
				} else {
					left = new AvlTreeNode(key, val);
					level = this.calLevel();
				}
				return this;
			}
			if (key > this.key) {
				if (right != null) {
					right = right.addNode(key, val);
					if (this.unbalanceRate() <= -2) {
						if (right.unbalanceRate() < 0) {
							return this.rrRotate();
						}
						if (right.unbalanceRate() > 0) {
							return this.rlRotate();
						}
					}
					level = this.calLevel();
				} else {
					right = new AvlTreeNode(key, val);
					level = this.calLevel();
				}
				return this;
			}
			throw new SameKeyExistException("same key exist when add node");
		}

		private AvlTreeNode removeNode(int key) throws NoSuchKeyExistException {
			if (key < this.key) {
				if (left != null) {
					left = left.removeNode(key);
					if (this.unbalanceRate() <= -2) {
						if (right.unbalanceRate() > 0) {
							return this.rlRotate();
						}
						if (right.unbalanceRate() <= 0) {
							return this.rrRotate();
						}
					}
					level = this.calLevel();
					return this;
				}
				throw new NoSuchKeyExistException("no such key exist when remove node");
			}
			if (key > this.key) {
				if (right != null) {
					right = right.removeNode(key);
					if (this.unbalanceRate() >= 2) {
						if (left.unbalanceRate() < 0) {
							return this.lrRotate();
						}
						if (left.unbalanceRate() >= 0) {
							return this.llRotate();
						}
					}
					level = this.calLevel();
					return this;
				}
				throw new NoSuchKeyExistException("no such key exist when remove node");
			}
			if (this.unbalanceRate() >= 0) {
				if (left != null) {
					AvlTreeNode maxNode = left.maxNode();
					this.key = maxNode.key;
					this.val = maxNode.val;
					left = left.removeNode(maxNode.key);
					level = this.calLevel();
				} else {
					return null;
				}
			} else {
				AvlTreeNode minNode = right.minNode();
				this.key = minNode.key;
				this.val = minNode.val;
				right = right.removeNode(minNode.key);
				level = this.calLevel();
			}
			return this;
		}

		/**
		 * ll旋转
		 */
		private AvlTreeNode llRotate() {
			AvlTreeNode newRootNode = left;
			left = newRootNode.right;
			newRootNode.right = this;
			level = this.calLevel();
			newRootNode.level = newRootNode.calLevel();
			return newRootNode;
		}

		/**
		 * lr旋转
		 */
		private AvlTreeNode lrRotate() {
			AvlTreeNode newRootNode = left.right;
			left.right = newRootNode.left;
			newRootNode.left = left;
			left = newRootNode.right;
			newRootNode.right = this;
			level = this.calLevel();
			newRootNode.left.level = newRootNode.left.calLevel();
			newRootNode.level = newRootNode.calLevel();
			return newRootNode;
		}

		/**
		 * rl旋转
		 */
		private AvlTreeNode rlRotate() {
			AvlTreeNode newRootNode = right.left;
			right.left = newRootNode.right;
			newRootNode.right = right;
			right = newRootNode.left;
			newRootNode.left = this;
			level = this.calLevel();
			newRootNode.right.level = newRootNode.right.calLevel();
			newRootNode.level = newRootNode.calLevel();
			return newRootNode;
		}

		/**
		 * rr旋转
		 */
		private AvlTreeNode rrRotate() {
			AvlTreeNode newRootNode = right;
			right = newRootNode.left;
			newRootNode.left = this;
			level = this.calLevel();
			newRootNode.level = newRootNode.calLevel();
			return newRootNode;
		}

		private int calLevel() {
			int leftLevel = (left == null) ? 0 : left.level;
			int rightLevel = (right == null) ? 0 : right.level;
			return Math.max(leftLevel, rightLevel) + 1;
		}

		private int unbalanceRate() {
			int leftLevel = (left == null) ? 0 : left.level;
			int rightLevel = (right == null) ? 0 : right.level;
			return leftLevel - rightLevel;
		}

		private AvlTreeNode maxNode() {
			while (right != null) {
				return right.maxNode();
			}
			return this;
		}

		private AvlTreeNode minNode() {
			while (left != null) {
				return left.minNode();
			}
			return this;
		}

		private int size() {
			int leftSize = (left == null) ? 0 : left.size();
			int rightSize = (right == null) ? 0 : right.size();
			return leftSize + rightSize + 1;
		}

	}

}
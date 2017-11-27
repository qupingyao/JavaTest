package Algorithm.Test;

import java.util.HashMap;
import java.util.Map;

public class RbTree {

	private RbTNode mRoot;

	private static final boolean RED = false;
	private static final boolean BLACK = true;

	public class RbTNode {
		boolean color;
		int key;
		RbTNode left;
		RbTNode right;
		RbTNode parent;

		public RbTNode(int key, boolean color, RbTNode parent, RbTNode left, RbTNode right) {
			this.key = key;
			this.color = color;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

		private int level() {
			int leftlevel = (left == null) ? 0 : left.level();
			int rightlevel = (right == null) ? 0 : right.level();
			return Math.max(leftlevel, rightlevel) + 1;
		}

	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (mRoot != null) {
			int order = 1;
			int nodeNum = (int) Math.pow(2, mRoot.level()) - 1;
			Map<Integer, RbTNode> entry = new HashMap<Integer, RbTNode>();
			entry.put(order, mRoot);
			for (int height = 1; order <= nodeNum; order++) {
				RbTNode rbTNode = entry.get(order);
				if (rbTNode != null) {
					buffer.append(String.format("%-2d(c=%-1s) ", rbTNode.key, rbTNode.color ? "b" : "r"));
					entry.put(2 * order, rbTNode.left);
					entry.put(2 * order + 1, rbTNode.right);
				} else {
					buffer.append(String.format("%-9s", "&nbsp"));
				}
				if (order == ((int) Math.pow(2, height) - 1)) {
					height++;
					buffer.append("\n");
				}
			}
		}
		return buffer.toString();
	}

	public RbTree() {
		mRoot = null;
	}

	private RbTNode parentOf(RbTNode node) {
		return node != null ? node.parent : null;
	}

	private boolean isRed(RbTNode node) {
		return ((node != null) && (node.color == RED)) ? true : false;
	}

	private void setBlack(RbTNode node) {
		if (node != null){
			node.color = BLACK;
		}
	}

	private void setRed(RbTNode node) {
		if (node != null){
			node.color = RED;
		}
	}

	private void leftRotate(RbTNode x) {
		RbTNode y = x.right;
		x.right = y.left;
		if (y.left != null){
			y.left.parent = x;
		}
		y.parent = x.parent;

		if (x.parent == null) {
			this.mRoot = y;
		} else {
			if (x.parent.left == x){
				x.parent.left = y;
			}else{
				x.parent.right = y;
			}
		}
		y.left = x;
		x.parent = y;
	}

	private void rightRotate(RbTNode y) {
		RbTNode x = y.left;
		y.left = x.right;
		if (x.right != null){
			x.right.parent = y;
		}
		x.parent = y.parent;

		if (y.parent == null) {
			this.mRoot = x;
		} else {
			if (y == y.parent.right){
				y.parent.right = x;
			}else{
				y.parent.left = x;
			}
		}
		x.right = y;
		y.parent = x;
	}

	
	
	public void insert(int key) {
		RbTNode node = new RbTNode(key, BLACK, null, null, null);
		if (node != null){
			insert(node);
		}
	}

	private void insert(RbTNode node) {
		int cmp;
		RbTNode y = null;
		RbTNode x = this.mRoot;

		while (x != null) {
			y = x;
			cmp = node.key - x.key;
			if (cmp < 0){
				x = x.left;
			}else{
				x = x.right;
			}
		}

		node.parent = y;
		if (y != null) {
			cmp = node.key - y.key;
			if (cmp < 0){
				y.left = node;
			}else{
				y.right = node;
			}
		} else {
			this.mRoot = node;
		}
		node.color = RED;
		insertFixUp(node);
	}
	
	private void insertFixUp(RbTNode node) {
		RbTNode parent, gparent;

		while (((parent = parentOf(node)) != null) && isRed(parent)) {
			gparent = parentOf(parent);

			if (parent == gparent.left) {
				RbTNode uncle = gparent.right;
				if ((uncle != null) && isRed(uncle)) {
					setBlack(uncle);
					setBlack(parent);
					setRed(gparent);
					node = gparent;
					continue;
				}
				if (parent.right == node) {
					RbTNode tmp;
					leftRotate(parent);
					tmp = parent;
					parent = node;
					node = tmp;
				}
				setBlack(parent);
				setRed(gparent);
				rightRotate(gparent);
			} else {
				RbTNode uncle = gparent.left;
				if ((uncle != null) && isRed(uncle)) {
					setBlack(uncle);
					setBlack(parent);
					setRed(gparent);
					node = gparent;
					continue;
				}

				if (parent.left == node) {
					RbTNode tmp;
					rightRotate(parent);
					tmp = parent;
					parent = node;
					node = tmp;
				}

				setBlack(parent);
				setRed(gparent);
				leftRotate(gparent);
			}
		}
		setBlack(this.mRoot);
	}



}
package Algorithm.RbTree;

import java.util.HashMap;
import java.util.Map;

import Algorithm.AvlTree.SameKeyExistException;
import Algorithm.AvlTree.AvlTree.AvlTreeNode;

public class RbTree {

	private final static String formatStr = "%-2d(c=%-1s,v=%s) ";

	private RbTreeNode root;

	public RbTree() {

	}

	public String getValue(int key) {
		if (root != null) {
			return root.getValue(key);
		}
		return null;
	}

	public boolean add(int key, String val) {
		if (root == null) {
			root = new RbTreeNode(key,val,RbTreeNode.black,null);
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
	
	/*
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
	}*/

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		if (root != null) {
			int order = 1;
			int nodeNum = (int) Math.pow(2, root.level()) - 1;
			Map<Integer, RbTreeNode> entry = new HashMap<Integer, RbTreeNode>();
			entry.put(order, root);
			for (int height = 1; order <= nodeNum; order++) {
				RbTreeNode rbTreeNode = entry.get(order);
				if (rbTreeNode != null) {
					buffer.append(String.format(formatStr, rbTreeNode.key, rbTreeNode.color?"b":"r", rbTreeNode.val));
					entry.put(2 * order, rbTreeNode.left);
					entry.put(2 * order + 1, rbTreeNode.right);
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

	public class RbTreeNode {
		
		private static final boolean red   = false;
		
		private static final boolean black = true;

		private int key;

		private String val;

		private boolean color;

		private RbTreeNode left;

		private RbTreeNode right;
		
		private RbTreeNode parent;

		private RbTreeNode(int key, String val,boolean color,RbTreeNode parent) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.parent = parent;
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

		private RbTreeNode addNode(int key, String val) throws SameKeyExistException {
			RbTreeNode tempNode = this;
			RbTreeNode parentNode = parent;
			while(tempNode!=null){
				if(key == tempNode.key){
					throw new SameKeyExistException("same key exist when add node");
				}
				parentNode = tempNode;
				if (key < tempNode.key) {
					tempNode = tempNode.left;
					continue;
				}
				if (key > tempNode.key) {
					tempNode = tempNode.right;
					continue;
				}
			}
			RbTreeNode rbTreeNode = new RbTreeNode(key, val, red, parentNode);
			if(key<parentNode.key){
				parentNode.left = rbTreeNode;
			}else{
				parentNode.right = rbTreeNode;
			}
			rbTreeNode.adjustToUp();
			return this;
		}
		
		private void adjustToUp(){
			if(parent!=null){
				if(parent.color==red){
					RbTreeNode uncleNode = this.getUncle();
					RbTreeNode grandparentNode = parent.parent;
					if(uncleNode==null || uncleNode.color==black){
						if(parent.isLeftSon()){
							if(this.isLeftSon()){
								grandparentNode = grandparentNode.llRotate();
							}else{
								grandparentNode = grandparentNode.lrRotate();
							}
						}else{
							if(this.isLeftSon()){
								grandparentNode = grandparentNode.rlRotate();
							}else{
								grandparentNode = grandparentNode.rrRotate();
							}
						}
					}else{
						parent.color = black;
						parent.getUncle().color = black;
						grandparentNode.color = red;
						grandparentNode.adjustToUp();
					}
				}
			}
		}
		
		private boolean isLeftSon() {
			if(parent!=null){
				return (parent.left == this);
			}
			return false;
		}
		
		private boolean isRightSon() {
			if(parent!=null){
				return (parent.right == this);
			}
			return false;
		}
		
		
		private RbTreeNode getBrother(){
			if(parent!=null){
				if(this==parent.left){
					return parent.right;
				}
				if(this==parent.right){
					return parent.left;
				}
			}
			return null;
		}
		
		private RbTreeNode getUncle(){
			if(parent!=null){
				return parent.getBrother();
			}
			return null;
		}
		
		private RbTreeNode getGrandParent(){
			if(parent!=null){
				return parent.parent;
			}
			return null;
		}

		/**
		 * ll调整
		 */
		private RbTreeNode llRotate() {
			System.out.println("llRotate");
			RbTreeNode newRootNode = left;
			left = newRootNode.right;
			if(left!=null){
				left.parent = this;
			}
			newRootNode.right = this;
			parent = newRootNode;
			newRootNode.color = black;
			color = red;
			return newRootNode;
		}

		/**
		 * lr调整
		 */
		private RbTreeNode lrRotate() {
			System.out.println("lrRotate");
			RbTreeNode newRootNode = left.right;
			left.right = newRootNode.left;
			if(left.right!=null){
				left.right.parent = left;
			}
			newRootNode.left = left;
			left.parent = newRootNode;
			left = newRootNode.right;
			left.parent  = this;
			newRootNode.right = this;
			parent = newRootNode;
			newRootNode.color = black;
			color = red;
			return newRootNode;
		}

		/**
		 * rl调整
		 */
		private RbTreeNode rlRotate() {
			System.out.println("rlRotate");
			RbTreeNode newRootNode = right.left;
			right.left = newRootNode.right;
			if(right.left!=null){
				right.left.parent = right;
			}
			newRootNode.right = right;
			right.parent = newRootNode;
			right = newRootNode.left;
			right.parent  = this;
			newRootNode.left = this;
			parent = newRootNode;
			newRootNode.color = black;
			color = red;
			return newRootNode;
		}

		/**
		 * rr调整
		 */
		private RbTreeNode rrRotate() {
			System.out.println("rrRotate");
			RbTreeNode newRootNode = right;
			right = newRootNode.left;
			if(right!=null){
				right.parent = this;
			}
			newRootNode.left = this;
			parent = newRootNode;
			newRootNode.color = black;
			color = red;
			return newRootNode;
		}

		private int size() {
			int leftSize = (left == null) ? 0 : left.size();
			int rightSize = (right == null) ? 0 : right.size();
			return leftSize + rightSize + 1;
		}
		
		private int level(){
			int leftlevel = (left == null) ? 0 : left.level();
			int rightlevel = (right == null) ? 0 : right.level();
			return Math.max(leftlevel,rightlevel) + 1;
		}

	}

}
package Algorithm.AvlTree;

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class AvlTreeNode{
	
	private int val;
	
	private int level = 1;
	
	private AvlTreeNode left;
	
	private AvlTreeNode right;
	
	public AvlTreeNode(int val){
		this.val = val;
	}
	
	public void addNode(int key){
		AvlTreeNode waitAddNode = null;
		if(val<key){
			if(right!=null){
				waitAddNode = right;
				waitAddNode.addNode(key);
			}else{
				waitAddNode = new AvlTreeNode(key);
				right = waitAddNode;
			}
		}else if(val>key){
			if(left!=null){
				waitAddNode = left;
				waitAddNode.addNode(key);
			}else{
				waitAddNode = new AvlTreeNode(key);
				left = waitAddNode;
			}
		}
		level = this.calLevel();
	}
	
	/*****************************************************************************
	 * ll旋转:a为e子树添加新节点后最小不平衡子树
	 * 	     a(h+2)                                          b(h+2)
	 * 	    /       \         d子树添加新节点后                                                   /           \
	 * 	  b(h+1)    c(h) -------------------------->  d(h+1)          a(h+1)  
	 *    /	 \        			(h>=1)			        /	 \          /  \
	 *  d(h)  e(h)					                f(h-1/h) g(h-1/h) e(h) c(h)
	 *   /  \									   
	 *f(h-1) g(h-1)
	 *****************************************************************************/
	private void llRotate(){
	}
	
	/*****************************************************************************
	 * lr旋转:a为d子树添加新节点后最小不平衡子树
	 * 	    a(h+2)                                        e(h+2)
	 * 	   /     \         e子树添加新节点后                                                /           \
	 * 	 b(h+1)   c(h) -------------------------->  b(h+1)         a(h+1)  
	 *   /   \			(h>=1)			            /    \         /	 \
	 * d(h)  e(h)					              d(h)  f(h-1/h) g(h-1/h) c(h)
	 * 	    /   \									   
	 *    f(h-1) g(h-1)
	 *****************************************************************************/
	private void lrRotate(){
	}
	
	/*****************************************************************************
	 * rl旋转:a为d子树添加新节点后最小不平衡子树
	 * 	    a(h+2)                                        d(h+2)
	 * 	   /     \         d子树添加新节点后                                                /            \
	 * 	 b(h)   c(h+1) -------------------------->  a(h+1)         c(h+1)  
	 *  	     /  \			(h>=1)			   /    \         /	    \
	 * 		   d(h) e(h)					    b(h)  f(h-1/h) g(h-1/h) e(h)
	 * 		  /   \									   
	 *    f(h-1) g(h-1)
	 *****************************************************************************/
	private void rlRotate(){
		AvlTreeNode oldRootNode  = this;
		AvlTreeNode rootNode = oldRootNode.right.left;
		oldRootNode.right = rootNode.left;
		oldRootNode.right.left = rootNode.right;
		rootNode.left = oldRootNode;
		rootNode.right = oldRootNode.right;
		oldRootNode = rootNode;
	}
	
	/*****************************************************************************
	 * rr旋转:a为e子树添加新节点后最小不平衡子树
	 * 	     a(h+2)                                          c(h+2)
	 * 	   /       \         e子树添加新节点后                                                   /           \
	 * 	 b(h)      c(h+1) -------------------------->  a(h+1)       e(h+1)  
	 *  	      /     \			(h>=1)			   /	\        /	 \
	 * 		    d(h)    e(h)					     b(h) d(/h) f(h-1/h) g(h-1/h)
	 * 		           /  \									   
	 *             f(h-1) g(h-1)
	 *****************************************************************************/
	private void rrRotate(){
	}
	   
	
	private int calLevel(){
		int leftLevel = (left==null)? 0:left.calLevel();
		int rightLevel = (right==null)? 0:right.calLevel();
		return Math.max(leftLevel, rightLevel)+1;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		int nodeNum = (int)Math.pow(2,level)-1;
		Queue<AvlTreeNode> queue = new LinkedList<AvlTreeNode>();
		queue.add(this);
		for(int i=0,height=1;i<nodeNum;i++){
			AvlTreeNode avlTreeNode = queue.poll();
			if(avlTreeNode!=null){
				buffer.append(String.format("%-5d",avlTreeNode.val));
				queue.add(avlTreeNode.left);
				queue.add(avlTreeNode.right);
			}else{
				buffer.append(String.format("%-5s","null"));
				queue.add(null);
				queue.add(null);
			}
			if(i == ((int)Math.pow(2,height)-2)){
				height++;
				buffer.append("\n");
			}
		}
		return buffer.toString();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public AvlTreeNode getLeft() {
		return left;
	}

	public void setLeft(AvlTreeNode left) {
		this.left = left;
	}

	public AvlTreeNode getRight() {
		return right;
	}

	public void setRight(AvlTreeNode right) {
		this.right = right;
	}
	
}
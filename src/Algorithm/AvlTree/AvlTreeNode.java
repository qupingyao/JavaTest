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
	 * ll旋转:a为d子树添加新节点后最小不平衡子树
	 * 	    a(h+2)                     a(h+3)           		 b(h+2)
	 * 	   /    \     d子树添加新节点后              /    \     llRotate()     /    \                       		 /    \
	 * 	 b(h+1) c(h) -------------> b(h+2) c(h) ------------> d(h+1) a(h+1)  
	 *   /	 \        (h>=0)		/	 \			           		 /   \         
	 * d(h)  e(h)				 d(h+1) e(h)                 	   e(h)  c(h)
	 *****************************************************************************/
	private void llRotate(){
		AvlTreeNode newRootNode = this.left;
		this.left = newRootNode.right;
		newRootNode.right = this;
	}
	
	/*****************************************************************************
	 * lr旋转:a为e子树添加新节点后最小不平衡子树
	 * 	    a(h+2)                     a(h+3)                     e(h+2)
	 * 	   /    \     e子树添加新节点后               /    \     lrRotate()     /       \
	 * 	 b(h+1) c(h) -------------> b(h+2) c(h) ------------> b(h+1)     a(h+1)
	 *   /   \		  (h>=0)         /  \			          /  \       /  \
	 * d(h)  e(h)	               d(h) e(h+1)			    d(h) e左子树  e右子树    c(h)
	 *****************************************************************************/
	private void lrRotate(){
		AvlTreeNode newRootNode = this.left.right;
		this.left.right = newRootNode.left;
		newRootNode.left = this.left;
		this.left = newRootNode.right;
		newRootNode.right = this;
	}
	
	/*****************************************************************************
	 * rl旋转:a为d子树添加新节点后最小不平衡子树
	 * 	  a(h+2)                      a(h+3)                      d(h+2)
	 * 	 /    \       d子树添加新节点后           /   \       rlRotate()     /       \ 
	 * b(h)  c(h+1)  -------------> b(h) c(h+2) ------------> a(h+1)     c(h+1)  
	 *  	  /  \	  (h>=0)		     /  \	              /   \      /  \
	 * 		d(h) e(h)				 d(h+1) e(h)            b(h) d左子树 d右子树   e(h)
	 *****************************************************************************/
	private void rlRotate(){
		AvlTreeNode newRootNode = this.right.left;
		this.right.left = newRootNode.right;
		newRootNode.right = this.right;
		this.right = newRootNode.left;
		newRootNode.left = this;
	}
	
	/*****************************************************************************
	 * rr旋转:a为e子树添加新节点后最小不平衡子树
	 * 	  a(h+2)                       a(h+3)                     c(h+2)
	 * 	 /    \       e子树添加新节点后              /  \       rrRotate()      /     \
	 * b(h)  c(h+1)  -------------> b(h) c(h+2) ------------>  a(h+1) e(h+1)  
	 *       /   \	  (h>=0)			 /	\                  /   \
	 * 	   d(h)  e(h)                  d(h) e(h+1)			b(h)   d(h)
	 *****************************************************************************/
	private void rrRotate(){
		AvlTreeNode newRootNode = this.right;
		this.right = newRootNode.right;
		newRootNode.left = this;
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
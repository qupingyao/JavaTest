package Algorithm.AvlTree;

import java.util.LinkedList;
import java.util.Queue;

public class AvlTreeNode{
	
	private int val;
	
	private int level = 1;
	
	private AvlTreeNode left;
	
	private AvlTreeNode right;
	
	public AvlTreeNode(int val){
		this.val = val;
	}
	
	public AvlTreeNode addNode(int key){
		if(key<val){
			if(left!=null){
				left = left.addNode(key);
				level = this.calLevel();
				if(this.calUnbalanceRate()>=2){
					if(this.left.calUnbalanceRate()>0){
						return this.llRotate();
					}else if(this.left.calUnbalanceRate()<0){
						return this.lrRotate();
					}
				}
			}else{
				left = new AvlTreeNode(key);
				level = this.calLevel();
			}
		}else if(key>val){
			if(right!=null){
				right = right.addNode(key);
				level = this.calLevel();
				if(this.calUnbalanceRate()<=-2){
					if(this.right.calUnbalanceRate()<0){
						return this.rrRotate();
					}else if(this.right.calUnbalanceRate()>0){
						return this.rlRotate();
					}
				}
			}else{
				right = new AvlTreeNode(key);
				level = this.calLevel();
			}
		}
		return this;
	}
	
	public AvlTreeNode removeNode(int key){
		if(key<val){
			if(left!=null){
				left = left.removeNode(key);
				level = this.calLevel();
				if(this.calUnbalanceRate()<=-2){
					if(this.right.calUnbalanceRate()>0){
						return this.rlRotate();
					}else if(this.right.calUnbalanceRate()<=0){
						return this.rrRotate();
					}
				}
			}
		}else if(key>val){
			if(right!=null){
				right = right.removeNode(key);
				level = this.calLevel();
				if(this.calUnbalanceRate()>=2){
					if(this.left.calUnbalanceRate()<0){
						return this.lrRotate();
					}else if(this.left.calUnbalanceRate()>=0){
						return this.llRotate();
					}
				}
			}
		}else{
			return this.removeRoot();
		}
		return this;
	}
	
	/*****************************************************************************
	 * ll旋转:a为添加删除节点后最小不平衡子树
	 * 	    a(h+2)                     a(h+3)           		 b(h+2)
	 * 	   /    \     d子树添加新节点后              /    \     llRotate()     /    \                       		 /    \
	 * 	 b(h+1) c(h) -------------> b(h+2) c(h) ------------> d(h+1) a(h+1)  
	 *   /	 \        (h>=0)		/	 \			           		 /   \         
	 * d(h)  e(h)				 d(h+1) e(h)                 	   e(h)  c(h)
	 * 
	 * 	    a(h+3)                    a(h+3)           		     b(h+2/h+3)
	 * 	   /    \     c子树删除节点后              /    \     llRotate()      /    \                       		 /    \
	 * 	 b(h+2) c(h+1) -----------> b(h+2) c(h) ------------> d(h+1) a(h+1/h+2)  
	 *   /	 \        (h>=0)		/	 \			           		 /    \         
	 * d(h+1) e(h/h+1)			 d(h+1) e(h/h+1)                  e(h/h+1) c(h)
	 *****************************************************************************/
	private AvlTreeNode llRotate(){
		AvlTreeNode newRootNode = this.left;
		this.left = newRootNode.right;
		newRootNode.right = this;
		this.level = this.calLevel();
		newRootNode.level = newRootNode.calLevel();
		return newRootNode;
	}
	
	/*****************************************************************************
	 * lr旋转:a为添加删除节点后最小不平衡子树
	 * 	    a(h+2)                     a(h+3)                     e(h+2)
	 * 	   /    \     e子树添加新节点后               /    \     lrRotate()     /       \
	 * 	 b(h+1) c(h) -------------> b(h+2) c(h) ------------> b(h+1)     a(h+1)
	 *   /   \		  (h>=0)         /  \			          /  \       /  \
	 * d(h)  e(h)	               d(h) e(h+1)			    d(h) e左子树  e右子树    c(h)
	 * 
	 * 	    a(h+3)                     a(h+3)                     e(h+2)
	 * 	   /    \     c子树删除节点后                   /    \     lrRotate()     /       \
	 * 	 b(h+2) c(h+1) -----------> b(h+2) c(h) ------------> b(h+1)     a(h+1)
	 *   /   \		  (h>=0)         /  \			          /  \       /  \
	 * d(h)  e(h+1)	               d(h) e(h+1)			    d(h) e左子树  e右子树    c(h)
	 *****************************************************************************/
	private AvlTreeNode lrRotate(){
		AvlTreeNode newRootNode = this.left.right;
		this.left.right = newRootNode.left;
		newRootNode.left = this.left;
		this.left = newRootNode.right;
		newRootNode.right = this;
		this.level = this.calLevel();
		newRootNode.left.level = newRootNode.left.calLevel();
		newRootNode.level = newRootNode.calLevel();
		return newRootNode;
	}
	
	/*****************************************************************************
	 * rl旋转:a为添加删除节点后最小不平衡子树
	 * 	  a(h+2)                      a(h+3)                      d(h+2)
	 * 	 /    \       d子树添加新节点后           /   \       rlRotate()     /       \ 
	 * b(h)  c(h+1)  -------------> b(h) c(h+2) ------------> a(h+1)     c(h+1)  
	 *  	  /  \	  (h>=0)		     /  \	              /   \      /  \
	 * 		d(h) e(h)				 d(h+1) e(h)            b(h) d左子树 d右子树   e(h)
	 * 
	 * 	  a(h+3)                      a(h+3)                      d(h+2)
	 * 	 /    \       b子树删除节点后               /   \       rlRotate()     /       \ 
	 * b(h+1) c(h+2) -------------> b(h) c(h+2) ------------> a(h+1)     c(h+1)  
	 *  	  /  \	  (h>=0)		     /  \	              /   \      /  \
	 * 	   d(h+1) e(h)				 d(h+1) e(h)            b(h) d左子树 d右子树   e(h)
	 *****************************************************************************/
	private AvlTreeNode rlRotate(){
		AvlTreeNode newRootNode = this.right.left;
		this.right.left = newRootNode.right;
		newRootNode.right = this.right;
		this.right = newRootNode.left;
		newRootNode.left = this;
		this.level = this.calLevel();
		newRootNode.right.level = newRootNode.right.calLevel();
		newRootNode.level = newRootNode.calLevel();
		return newRootNode;
	}
	
	/*****************************************************************************
	 * rr旋转:a为添加删除节点后最小不平衡子树
	 * 	  a(h+2)                       a(h+3)                     c(h+2)
	 * 	 /    \       e子树添加新节点后              /  \       rrRotate()      /     \
	 * b(h)  c(h+1)  -------------> b(h) c(h+2) ------------>  a(h+1) e(h+1)  
	 *       /   \	  (h>=0)			 /	\                  /   \
	 * 	   d(h)  e(h)                  d(h) e(h+1)			b(h)   d(h)
	 * 
	 * 	  a(h+3)                       a(h+3)                    c(h+2/h+3)
	 * 	 /    \       b子树删除节点后                  /  \       rrRotate()      /      \
	 * b(h+1) c(h+2) -------------> b(h) c(h+2) ------------> a(h+1/h+2) e(h+1)  
	 *       /   \	  (h>=0)			 /	  \                /   \
	 * 	 d(h/h+1) e(h+1)             d(h/h+1) e(h+1)		b(h)   d(h/h+1)
	 *****************************************************************************/
	private AvlTreeNode rrRotate(){
		AvlTreeNode newRootNode = this.right;
		this.right = newRootNode.left;
		newRootNode.left = this;
		this.level = this.calLevel();
		newRootNode.level = newRootNode.calLevel();
		return newRootNode;
	}
	
	private AvlTreeNode removeRoot(){
		if(left==null && right==null){
			return null;
		}else{
			int leftLevel = (left==null)? 0:left.level;
			int rightLevel = (right==null)? 0:right.level;
			if(leftLevel>=rightLevel){
				val = left.val;
				left = left.removeRoot();
				level = this.calLevel();
				return this;
			}else{
				val = right.val;
				right = right.removeRoot();
				level = this.calLevel();
				return this;
			}
		}
	}
	
	private int calLevel(){
		int leftLevel = (left==null)? 0:left.level;
		int rightLevel = (right==null)? 0:right.level;
		return Math.max(leftLevel, rightLevel)+1;
	}
	
	private int calUnbalanceRate(){
		int leftLevel = (left==null)? 0:left.level;
		int rightLevel = (right==null)? 0:right.level;
		return leftLevel-rightLevel;
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
				buffer.append(String.format("%-2d(%-1d)",avlTreeNode.val,avlTreeNode.level));
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
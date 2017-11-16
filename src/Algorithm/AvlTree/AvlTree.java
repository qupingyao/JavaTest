package Algorithm.AvlTree;

import java.util.LinkedList;
import java.util.Queue;

import javax.print.attribute.Size2DSyntax;
import javax.swing.text.DefaultEditorKit.InsertContentAction;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

public class AvlTree{
	
	private AvlTreeNode root;
	
	public AvlTree(){
		
	}
	
	public boolean add(int key){
		if(root == null){
			root = new AvlTreeNode(key);
		}else{
			try{
				root = root.addNode(key);
			}catch(SameKeyExistException e){
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public boolean remove(int key){
		if(root != null){
			try{
				root = root.removeNode(key);
			}catch(NoSuchKeyExistException e){
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public boolean isContain(int key){
		if(root != null){
			return root.isContain(key);
		}
		return false;
	}
	
	public int height(){
		if(root != null){
			return root.level;
		}
		return 0;
	}
	
	public int size(){
		if(root != null){
			return root.size();
		}
		return 0;
	}
	
	@Override
	public String toString(){
		StringBuffer buffer = new StringBuffer();
		if(root != null){
			int nodeNum = (int)Math.pow(2, root.level) - 1;
			Queue<AvlTreeNode> queue = new LinkedList<AvlTreeNode>();
			queue.add(root);
			for(int i = 0, height = 1; i < nodeNum; i++){
				AvlTreeNode avlTreeNode = queue.poll();
				if(avlTreeNode != null){
					buffer.append(String.format("%-2d(%-1d)", avlTreeNode.val, avlTreeNode.level));
					queue.add(avlTreeNode.left);
					queue.add(avlTreeNode.right);
				}else{
					buffer.append(String.format("%-5s", "null"));
					queue.add(null);
					queue.add(null);
				}
				if(i == ((int)Math.pow(2, height) - 2)){
					height++;
					buffer.append("\n");
				}
			}
		}
		return buffer.toString();
	}
	
	public class AvlTreeNode{
		
		private int val;
		
		private int level = 1;
		
		private AvlTreeNode left;
		
		private AvlTreeNode right;
		
		private AvlTreeNode(int val){
			this.val = val;
		}
		
		private AvlTreeNode addNode(int key)throws SameKeyExistException{
			if(key < val){
				if(left != null){
					left = left.addNode(key);
					level = this.calLevel();
					if(this.unbalanceRate() >= 2){
						if(left.unbalanceRate() > 0){
							return this.llRotate();
						}
						if(left.calLevel() < 0){
							return this.lrRotate();
						}
					}
				}else{
					left = new AvlTreeNode(key);
					level = this.calLevel();
				}
				return this;
			}
			if(key > val){
				if(right != null){
					right = right.addNode(key);
					level = this.calLevel();
					if(this.unbalanceRate() <= -2){
						if(right.unbalanceRate() < 0){
							return this.rrRotate();
						}
						if(right.unbalanceRate() > 0){
							return this.rlRotate();
						}
					}
				}else{
					right = new AvlTreeNode(key);
					level = this.calLevel();
				}
				return this;
			}
			throw new SameKeyExistException("exist when add key");
		}
		
		private AvlTreeNode removeNode(int key)throws NoSuchKeyExistException{
			if(key < val){
				if(left != null){
					left = left.removeNode(key);
					level = this.calLevel();
					if(this.unbalanceRate() <= -2){
						if(right.unbalanceRate() > 0){
							return this.rlRotate();
						}
						if(right.unbalanceRate() <= 0){
							return this.rrRotate();
						}
					}
					return this;
				}
				throw new NoSuchKeyExistException("exist when remove key");
			}
			if(key > val){
				if(right != null){
					right = right.removeNode(key);
					level = this.calLevel();
					if(this.unbalanceRate() >= 2){
						if(left.unbalanceRate() < 0){
							return this.lrRotate();
						}
						if(left.unbalanceRate() >= 0){
							return this.llRotate();
						}
					}
					return this;
				}
				throw new NoSuchKeyExistException("exist when remove key");
			}
			if(this.unbalanceRate() >= 0){
				if(left != null){
					AvlTreeNode maxNode = left.maxNode();
					val = maxNode.val;
					left = left.removeNode(maxNode.val);
					level = this.calLevel();
				}else{
					return null;
				}
			}else{
				AvlTreeNode minNode = right.minNode();
				val = minNode.val;
				right = right.removeNode(minNode.val);
				level = this.calLevel();
			}
			return this;
		}
		
		private AvlTreeNode maxNode(){
			while(right != null){
				return right.maxNode();
			}
			return this;
		}
		
		private AvlTreeNode minNode(){
			while(left != null){
				return left.minNode();
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
			AvlTreeNode newRootNode = left;
			left = newRootNode.right;
			newRootNode.right = this;
			level = this.calLevel();
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
			AvlTreeNode newRootNode = right;
			right = newRootNode.left;
			newRootNode.left = this;
			level = this.calLevel();
			newRootNode.level = newRootNode.calLevel();
			return newRootNode;
		}
		
		private int calLevel(){
			int leftLevel = (left == null)? 0:left.level;
			int rightLevel = (right == null)? 0:right.level;
			return Math.max(leftLevel, rightLevel) + 1;
		}
		
		private int unbalanceRate(){
			int leftLevel = (left == null)? 0:left.level;
			int rightLevel = (right == null)? 0:right.level;
			return leftLevel - rightLevel;
		}
		
		private boolean isContain(int key){
			if(key < val){
				if(left != null){
					return left.isContain(key);
				}
				return false;
			}
			if(key > val){
				if(right != null){
					return right.isContain(key);
				}
				return false;
			}
			return true;
		}
		
		private int size(){
			int leftSize = (left == null)? 0:left.size();
			int rightSize = (right == null)? 0:right.size();
			return leftSize + rightSize + 1;
		}

	}
	
	
	
}
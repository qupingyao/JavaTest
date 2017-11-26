package Algorithm.RbTree;

public class RbTreeNode{
	
	private int val;
	
	private int level = 1;
	
	private RbTreeNode left;
	
	private RbTreeNode right;
	
	private RbTreeNode parent;
	
	public RbTreeNode(int val,RbTreeNode parent){
		this.val = val;
	}
	
	
	
}
package Algorithm.AvlTree;

import java.awt.SecondaryLoop;
import java.awt.event.MouseWheelEvent;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.spi.IIOServiceProvider;

import org.omg.CORBA.PRIVATE_MEMBER;

public class AvlTreeNode{
	
	private int val;
	
	private int level;
	
	private AvlTreeNode left;
	
	private AvlTreeNode right;
	
	public AvlTreeNode(int val){
		this.val = val;
	}
	
	public static void addNode(AvlTreeNode node,int key){
		if(node!=null){
			if(node.val<key){
				addNode(node.right, key);
			}else if(node.val>key){
				addNode(node.left, key);
			}
		}else{
			node = new AvlTreeNode(key);
		}
		node.level = calLevel(node);
	}
	
	private static int calLevel(AvlTreeNode node){
		if(node!=null){
			return Math.max(calLevel(node.left), calLevel(node.right))+1;
		}else{
			return 0;
		}
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
	
	public void print(){
		int num = (int)Math.pow(2,level)-1;
		Queue<AvlTreeNode> queue = new LinkedList<AvlTreeNode>();
		queue.add(this);
		int i = 0;
		while(i<num){
			AvlTreeNode avlTreeNode = queue.poll();
			if(avlTreeNode!=null){
				System.out.println(avlTreeNode.val);
				queue.add(avlTreeNode.left);
				queue.add(avlTreeNode.right);
				i = i+2;
			}else{
				System.out.println(avlTreeNode.val);
				queue.add(null);
				queue.add(null);
				i = i+2;
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		Queue<String> queue = new LinkedList<String>();
		queue.add(null);
		queue.add("111");
		queue.add(null);
		queue.add("222");
		System.out.println(queue.size());
		for(Iterator iter = queue.iterator(); iter.hasNext();) {
			System.out.println(iter.next());
		}
	}

	
}
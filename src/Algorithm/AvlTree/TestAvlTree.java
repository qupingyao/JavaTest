package Algorithm.AvlTree;


public class TestAvlTree{
	
	public static void main(String[] args) {
		AvlTreeNode avlTreeNode = new AvlTreeNode(20);
		int[] array = {10,30,5,2,7,15,40,50,35,1,4,8,0,3};
		for(int i=0;i<array.length;i++){
			avlTreeNode = avlTreeNode.addNode(array[i]);
			System.out.format("after add %d:\n",array[i]);
			System.out.println(avlTreeNode);
		}
		System.out.println(avlTreeNode);
		int[] dArray = {37,20,35,15,40,50,30,0,1,5,3,4,10,7,8};
		for(int i=0;i<dArray.length;i++){
			avlTreeNode = avlTreeNode.removeNode(dArray[i]);
			System.out.format("after remove %d:\n",dArray[i]);
			System.out.println(avlTreeNode);
		}
	}

	
}
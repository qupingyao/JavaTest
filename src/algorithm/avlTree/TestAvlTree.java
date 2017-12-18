package algorithm.avlTree;

public class TestAvlTree {

	public static void main(String[] args) {
		AvlTree avlTree = new AvlTree();
		int[] kArray = { 20, 10, 30, 5, 2, 7, 15, 15, 40, 50, 35, 1, 4, 8, 0, 3 };
		String[] vArray = { "a", "b", "c", "d", "e", "f", "g", "g", "h", "i", "j", "k", "l", "m", "n", "o" };
		for (int i = 0; i < kArray.length; i++) {
			avlTree.add(kArray[i], vArray[i]);
			System.out.format("after add %d:\n", kArray[i]);
			System.out.println(avlTree);
		}
		int[] dArray = { 20, 35, 15, 0, 3, 8, 5, 5, 50, 40, 7, 4, 2, 10, 30, 1 };
		for (int i = 0; i < dArray.length; i++) {
			avlTree.remove(dArray[i]);
			System.out.format("after remove %d:\n", dArray[i]);
			System.out.println(avlTree);
		}
	}

}
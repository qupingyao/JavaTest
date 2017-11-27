package Algorithm.Test;

public class TestRbTree {
	
	public static void main(String[] args) {
		RbTree rbTree = new RbTree();
		int[] kArray = { 100, 90, 80, 70, 60, 75, 72, 85, 120, 140, 110, 105};
//		String[] vArray = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l" };
		for (int i = 0; i < kArray.length; i++) {
			rbTree.insert(kArray[i]);
			System.out.format("after add %d:\n", kArray[i]);
			System.out.println(rbTree);
		}
	}
}

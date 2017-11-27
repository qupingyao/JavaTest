package Collection.Iterator;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		MyList myList = new MyList(Arrays.asList("A", "B", "C"));
		for (String str : myList) {
			System.out.println(str);
		}
	}
}

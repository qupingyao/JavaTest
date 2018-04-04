package rpc;

public class C implements CInterface {

	@Override
	public String sayToUpperCase(String word) {
		String str = word.toUpperCase();
		System.out.println("I say:" + str);
		return str;
	}

}

package rpc.myFramework;

public class B implements BInterface {

	@Override
	public String sayReverse(String word) {
		String str = new StringBuffer(word).reverse().toString();
		System.out.println("I say:" + str);
		return str;
	}

}

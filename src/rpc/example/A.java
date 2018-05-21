package rpc.example;

public class A implements AInterface {

	@Override
	public String say(String word) {
		System.out.println("I say:" + word);
		return word;
	}

}

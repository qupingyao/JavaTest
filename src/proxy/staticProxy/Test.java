package proxy.staticProxy;

public class Test {

	public static void main(String[] args) {
		AInterface a = new A();
		AProxy aProxy = new AProxy(a);
		aProxy.say("I'm a boy");
		aProxy.cal(1);
		aProxy.getObject().getObject();
		System.out.println();
		Father f = new Father();
		FProxy fProxy = new FProxy(f);
		fProxy.say("I'm a boy");
		fProxy.cal(1);
		fProxy.getObject().getObject();
	}
}

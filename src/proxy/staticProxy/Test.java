package proxy.staticProxy;

public class Test {

	public static void main(String[] args) {
		AInterface a = new A();
		AProxy proxy = new AProxy(a);
		proxy.say("I have a dog");
		proxy.cal(1);
		proxy.getObj().getObj();
	}
}

package proxy.staticProxy;

public class AProxy implements AInterface {

	private AInterface target;

	public AProxy(AInterface target) {
		this.target = target;
	}

	@Override
	public void say(String word) {
		System.out.println("target start say");
		target.say(word);
		System.out.println("target end say");
	}

	@Override
	public void cal(int num) {
		System.out.println("target start cal");
		target.cal(num);
		System.out.println("target end cal");
	}

	@Override
	public AInterface getObj() {
		System.out.println("target start getObj");
		AInterface object = target.getObj();
		System.out.println("target end getObj");
		return this;
	}
}

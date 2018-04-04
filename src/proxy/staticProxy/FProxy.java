package proxy.staticProxy;

/**
 * 代理类
 */
public class FProxy extends Father {

	private Father target;

	public FProxy(Father target) {
		this.target = target;
	}

	@Override
	public void say(String word) {
		System.out.println("proxy method say start");
		super.say(word);
		System.out.println("proxy method say end");
	}

	@Override
	public void cal(int num) {
		System.out.println("proxy method cal start");
		target.cal(num);
		System.out.println("proxy method cal end");
	}

	@Override
	public Father getObject() {
		System.out.println("proxy method getObject start");
		Father object = target.getObject();
		System.out.println("proxy method getObject end");
		return this;
	}
}

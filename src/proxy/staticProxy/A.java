package proxy.staticProxy;

import proxy.staticProxy.A;

/**
 * 目标类
 */
public class A implements AInterface {

	@Override
	public void say(String word) {
		System.out.println("I say:" + word);
	}

	@Override
	public void cal(int num) {
		System.out.println("I cal:" + num);
	}

	@Override
	public AInterface getObject() {
		System.out.println("I get Object");
		return this;
	}

}

package proxy.staticProxy;

import proxy.staticProxy.A;

public class A implements AInterface {
	
	@Override
	public void say(String word) {
		System.out.println("I say:" + word);
		this.cal(10);
	}
	
	@Override
	public void cal(int num) {
		System.out.println("I cal:" + num);
	}
	
	@Override
	public AInterface getObj() {
		System.out.println("get mySelf");
		return this;
	}

}

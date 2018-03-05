package proxy.dynamicProxy.jdkProxy1;

import  proxy.dynamicProxy.jdkProxy1.AInterface;

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
	public A getObj() {
		System.out.println("get mySelf");
		return this;
	}

}
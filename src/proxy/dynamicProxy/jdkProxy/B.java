package proxy.dynamicProxy.jdkProxy;

import  proxy.dynamicProxy.jdkProxy.AInterface;

public class B {

	@Override
	public int hashCode() {
		System.out.println("hashCode");
		return 5;
	}
	public static void main(String[] args) {
		B b = new B();
		System.out.println(b);
	}
}

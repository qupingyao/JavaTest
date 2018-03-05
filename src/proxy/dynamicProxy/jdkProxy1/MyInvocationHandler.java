package proxy.dynamicProxy.jdkProxy1;

import java.lang.reflect.Method;

public class MyInvocationHandler {

	private Object target;

	public MyInvocationHandler(Object target) {
		this.target = target;
	}

	public Object invoke(Method method, Object[] args) throws Throwable {
		System.out.println("proxy start");
		method.invoke(target, args);
		System.out.println("proxy end");
		return null;
	}

}

package proxy.dynamicProxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

	private Object target;

	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy method " + method.getName() + " start");
		method.invoke(target, args);
		System.out.println("proxy method " + method.getName() + " end");
		return proxy;
	}

}

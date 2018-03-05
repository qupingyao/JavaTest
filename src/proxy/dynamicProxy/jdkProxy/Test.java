package proxy.dynamicProxy.jdkProxy;

import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
				new Class[] { AInterface.class }, new MyInvocationHandler(new A()));
		proxy.say("I have a dog");
		proxy.cal(1);
		proxy.getObj().getObj();
		System.out.println("###########################################");
		$Proxy0 proxy1 = new $Proxy0(new MyInvocationHandler(new A()));
		proxy1.say("I have a dog");
		proxy1.cal(1);
		proxy1.getObj().getObj();
	}
}

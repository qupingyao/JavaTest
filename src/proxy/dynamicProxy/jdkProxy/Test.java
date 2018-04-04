package proxy.dynamicProxy.jdkProxy;

import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {

		// System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles",
		// "true");/** 显示代理类生成字节码文件**/
		AInterface proxy1 = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(),
				new Class[] { AInterface.class }, new MyInvocationHandler(new A()));
		proxy1.say("I'm a boy");
		proxy1.cal(1);
		proxy1.getObject().getObject();
		System.out.println();
		AInterface proxy2 = new $Proxy0(new MyInvocationHandler(new A()));
		proxy2.say("I'm a boy");
		proxy2.cal(1);
		proxy2.getObject().getObject();
		System.out.println();
		AInterface proxy3 = new $Proxy1(new MyInvocationHandler(new A()));
		proxy3.say("I'm a boy");
		proxy3.cal(1);
		proxy3.getObject().getObject();
	}

}

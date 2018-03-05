package proxy.dynamicProxy.cglibProxy;

import net.sf.cglib.core.DebuggingClassWriter;

public class Test {

	public static void main(String[] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "cglib"); 
		MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor();
		A a1 = (A)myMethodInterceptor.getInstance(new A());  
		a1.say("I have a dog");
//		a1.cal(1);
//		a1.getObj().getObj();
	}
}

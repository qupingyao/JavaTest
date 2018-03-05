package proxy.dynamicProxy.jdkProxy1;

public class Test {

	public static void main(String[] args) {
		A a = new A();
		MyInvocationHandler myInvocationHandler = new MyInvocationHandler(a);
		$Proxy0 proxy1 = new $Proxy0(myInvocationHandler);
		proxy1.say("I have a dog");
//		proxy1.cal(1);
//		proxy1.getObj().getObj();
	}
}

package rpc.myFramework;

import java.lang.reflect.Proxy;

public class RpcClient {

	public static <T> T getService(final Class<T> interfaceClass) {
		T object = (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] { interfaceClass },
				new ServerInvocationHandler(interfaceClass));
		return object;
	}

}

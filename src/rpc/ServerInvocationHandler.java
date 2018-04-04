package rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class ServerInvocationHandler implements InvocationHandler {

	private Class interfaceClass;

	public ServerInvocationHandler(Class interfaceClass) {
		super();
		this.interfaceClass = interfaceClass;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		RegisterCenterInterface registerCenterInterface = (RegisterCenterInterface) Proxy.newProxyInstance(RegisterCenterInterface.class.getClassLoader(), new Class<?>[] { RegisterCenterInterface.class },
				new RegisterInvocationHandler(RegisterCenter.registerCenterHost,RegisterCenter.registerCenterPort));
		RegisterMsg registerMsg = registerCenterInterface.getRegisterMsg(interfaceClass);
		if (registerMsg != null) {
			Socket socket = new Socket(registerMsg.getHost(), registerMsg.getPort());
			try {
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				try {
					output.writeObject(interfaceClass);
					output.writeObject(method.getName());
	                output.writeObject(method.getParameterTypes());
					output.writeObject(args);
					Object result = input.readObject();
					if (result instanceof Throwable) {
						throw (Throwable) result;
					}
					return result;
				} finally {
					if (input != null) {
						input.close();
					}
					if (output != null) {
						output.close();
					}
				}
			} finally {
				if (socket != null) {
					socket.close();
				}
			}
		} else {
			throw new ClassNotFoundException(interfaceClass + " is not register");
		}
	}

}

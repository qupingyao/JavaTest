package rpc.example;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

public class RegisterInvocationHandler implements InvocationHandler {

	private String host;

	private int port;

	public RegisterInvocationHandler(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Socket socket = new Socket(host, port);
		ObjectInputStream input = null;
		ObjectOutputStream output = null;
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			input = new ObjectInputStream(socket.getInputStream());
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
	}

}

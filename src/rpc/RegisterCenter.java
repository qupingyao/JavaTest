package rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RegisterCenter implements RegisterCenterInterface {

	public static final String registerCenterHost = "127.0.0.1";

	public static final int registerCenterPort = 80;

	private static HashMap<Class, ArrayList<RegisterMsg>> registerMap = new HashMap<Class, ArrayList<RegisterMsg>>();

	private static final RegisterCenter instance = new RegisterCenter();

	private RegisterCenter() {

	}

	public static void start() {
		System.out.println("registerCenter start");
		ServerSocket server = null;
		try {
			server = new ServerSocket(registerCenterPort);
			while (true) {
				final Socket socket = server.accept();
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
							ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
							try {
								String methodName = (String) input.readObject();
								Class<?>[] methodParameterTypes = (Class<?>[]) input.readObject();
								Object[] args = (Object[]) input.readObject();
								Method implMethod = RegisterCenter.class.getMethod(methodName, methodParameterTypes);
								try {
									Object result = implMethod.invoke(instance, args);
									output.writeObject(result);
								} catch (Throwable t) {
									output.writeObject(t);
								}
							} finally {
								if (input != null) {
									input.close();
								}
								if (output != null) {
									output.close();
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
								if (socket != null) {
									socket.close();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (server != null) {
					server.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void register(Class interfaceClass, String host, int port) {
		RegisterMsg registerMsg = new RegisterMsg(host, port);
		ArrayList<RegisterMsg> registerMsgList = registerMap.containsKey(interfaceClass)
				? registerMap.get(interfaceClass) : new ArrayList<RegisterMsg>();
		registerMsgList.add(registerMsg);
		registerMap.put(interfaceClass, registerMsgList);
		System.out.println("registerCenter register success, interfaceClass:" + interfaceClass.getName()
				+ ", server-addr->" + host + ":" + port);
	}

	@Override
	public RegisterMsg getRegisterMsg(Class interfaceClass) {
		ArrayList<RegisterMsg> registerMsgList = registerMap.get(interfaceClass);
		if (registerMsgList != null && registerMsgList.size() > 0) {
			return registerMsgList.get(new Random().nextInt(registerMsgList.size()));
		}
		return null;
	}

}
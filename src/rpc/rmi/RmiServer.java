package rpc.rmi;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer {

	private static final String serverHost = "127.0.0.1";

	public static void publish(int port, String serverName, Remote obj) {
		try {
			Registry registry = LocateRegistry.getRegistry(RegisterCenter.registerCenterHost,
					RegisterCenter.registerCenterPort);
			UnicastRemoteObject.exportObject(obj, port);
			registry.rebind(serverName, obj);
			System.out.println("server publish success, objClass:" + obj.getClass().getName() + ", server-addr->"
					+ serverHost + ":" + port);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

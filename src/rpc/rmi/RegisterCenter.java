package rpc.rmi;

import java.rmi.registry.LocateRegistry;

public class RegisterCenter {

	public static final String registerCenterHost = "127.0.0.1";

	public static final int registerCenterPort = 80;

	public static void start() {
		try {
			System.out.println("registerCenter start");
			LocateRegistry.createRegistry(registerCenterPort);
			while (true) {
				try {
					Thread.sleep(10000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

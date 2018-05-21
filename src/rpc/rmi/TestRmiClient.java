package rpc.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TestRmiClient {

	public static void main(String[] args) {

		try {
			Registry registry = LocateRegistry.getRegistry(RegisterCenter.registerCenterHost,
					RegisterCenter.registerCenterPort);
			AInterface a = (AInterface) registry.lookup(TestRmiServer.serverNameA);
			BInterface b = (BInterface) registry.lookup(TestRmiServer.serverNameB);
			CInterface c = (CInterface) registry.lookup(TestRmiServer.serverNameC);
			System.out.println(a.say("hello"));
			System.out.println(b.sayReverse("abcde"));
			System.out.println(c.sayToUpperCase("abcde"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

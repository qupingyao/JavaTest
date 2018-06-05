package rpc.myFramework;

import rpc.myFramework.AInterface;
import rpc.myFramework.BInterface;
import rpc.myFramework.CInterface;
import rpc.myFramework.RpcClient;

public class TestRpcClient {

	public static void main(String[] args) {
		AInterface a = RpcClient.getService(AInterface.class);
		BInterface b = RpcClient.getService(BInterface.class);
		CInterface c = RpcClient.getService(CInterface.class);
		System.out.println(a.say("abcde"));
		System.out.println(b.sayReverse("abcde"));
		System.out.println(c.sayToUpperCase("abcde"));
	}

}

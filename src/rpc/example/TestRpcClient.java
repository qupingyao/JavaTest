package rpc.example;

import rpc.example.AInterface;
import rpc.example.BInterface;
import rpc.example.CInterface;
import rpc.example.RpcClient;

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

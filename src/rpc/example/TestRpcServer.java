package rpc.example;

import rpc.example.A;
import rpc.example.AInterface;
import rpc.example.B;
import rpc.example.BInterface;
import rpc.example.C;
import rpc.example.CInterface;
import rpc.example.RpcServer;

public class TestRpcServer {

	public static void main(String[] args) {
		RpcServer.publish(AInterface.class, A.class, 8080);
		RpcServer.publish(BInterface.class, B.class, 8080);
		RpcServer.publish(CInterface.class, C.class, 8081);
	}

}

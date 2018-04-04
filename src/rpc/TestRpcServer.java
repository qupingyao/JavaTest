package rpc;

import rpc.A;
import rpc.AInterface;
import rpc.B;
import rpc.BInterface;
import rpc.C;
import rpc.CInterface;
import rpc.RpcServer;

public class TestRpcServer {

	public static void main(String[] args) {
		RpcServer.publish(AInterface.class, A.class,8080);
		RpcServer.publish(BInterface.class, B.class,8080);
		RpcServer.publish(CInterface.class, C.class,8081);
	}

}

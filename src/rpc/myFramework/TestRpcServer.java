package rpc.myFramework;

import rpc.myFramework.A;
import rpc.myFramework.AInterface;
import rpc.myFramework.B;
import rpc.myFramework.BInterface;
import rpc.myFramework.C;
import rpc.myFramework.CInterface;
import rpc.myFramework.RpcServer;

public class TestRpcServer {

	public static void main(String[] args) {
		RpcServer.publish(AInterface.class, A.class, 8080);
		RpcServer.publish(BInterface.class, B.class, 8080);
		RpcServer.publish(CInterface.class, C.class, 8081);
	}

}

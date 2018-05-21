package rpc.rmi;

public class TestRmiServer {

	public static final String serverNameA = "myServerA";

	public static final String serverNameB = "myServerB";

	public static final String serverNameC = "myServerC";

	public static void main(String[] args) {
		RmiServer.publish(8080, serverNameA, new A());
		RmiServer.publish(8080, serverNameB, new B());
		RmiServer.publish(8081, serverNameC, new C());
	}

}
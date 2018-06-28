package network.socket.longConnection.example.server;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerCenter {

	public static final String serverHost = "127.0.0.1";

	public static final int serverPort = 8080;

	public static void start() {
		System.out.println("server center start");
		ServerSocket server = null;
		try {
			server = new ServerSocket(serverPort);
			while (true) {
				final Socket socket = server.accept();
				new Thread(new Server(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}

package network.socket.longConnection.example.server;

import java.io.OutputStream;

public class ServerDataSender {

	private Server server;

	public ServerDataSender(Server server) {
		this.server = server;
	}

	public synchronized void sendData(byte[] data) {
		if (!server.isClose()) {
			try {
				OutputStream output = server.getSocket().getOutputStream();
				output.write(data);
			} catch (Exception e) {
				e.printStackTrace();
				server.closeSocket();
			}
		}
	}
}

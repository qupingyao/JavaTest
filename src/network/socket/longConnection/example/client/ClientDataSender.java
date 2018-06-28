package network.socket.longConnection.example.client;

import java.io.OutputStream;

public class ClientDataSender {

	private Client client;

	public ClientDataSender(Client client) {
		this.client = client;
	}

	public synchronized void sendData(byte[] data) {
		if (!client.isClose()) {
			try {
				OutputStream output = client.getSocket().getOutputStream();
				output.write(data);
			} catch (Exception e) {
				e.printStackTrace();
				client.closeSocket();
			}
		}
	}
}

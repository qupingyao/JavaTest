package network.socket.longConnection.example.client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ClientMessageDataProcesor implements ClientDataProcesor {

	private Client client;

	public ClientMessageDataProcesor(Client client) {
		this.client = client;
	}

	@Override
	public void Process() {
		if (!client.isClose()) {
			try {
				InputStream input = client.getSocket().getInputStream();
				int length = input.read();
				ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
				for (int i = 0; i < length; i++) {
					int n = input.read();
					byteArrOut.write(n);
				}
				System.out.println("server message: " + byteArrOut.toString(network.socket.Constant.defaultCharset));
			} catch (Exception e) {
				e.printStackTrace();
				client.closeSocket();
			}
		}

	}
}

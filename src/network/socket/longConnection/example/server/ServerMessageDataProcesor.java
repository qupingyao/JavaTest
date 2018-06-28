package network.socket.longConnection.example.server;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class ServerMessageDataProcesor implements ServerDataProcesor {

	private Server server;

	public ServerMessageDataProcesor(Server server) {
		this.server = server;
	}

	@Override
	public void Process() {
		if (!server.isClose()) {
			try {
				InputStream input = server.getSocket().getInputStream();
				int length = input.read();
				ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
				for (int i = 0; i < length; i++) {
					int n = input.read();
					byteArrOut.write(n);
				}
				System.out.println("client message: " + byteArrOut.toString(network.socket.Constant.defaultCharset));
			} catch (Exception e) {
				e.printStackTrace();
				server.closeSocket();
			}
		}

	}
}

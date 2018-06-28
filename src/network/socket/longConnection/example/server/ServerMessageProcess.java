package network.socket.longConnection.example.server;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import network.socket.longConnection.example.message.Constant;

public class ServerMessageProcess implements Runnable {

	private Server server;

	public ServerMessageProcess(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
			InputStream input = server.getSocket().getInputStream();
			while (true) {
				byte type = (byte) input.read();
				if (type == Constant.msgTypeNormal) {
					int length = input.read();
					ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
					for (int i = 0; i < length; i++) {
						int n = input.read();
						byteArrOut.write(n);
					}
					System.out
							.println("client message: " + byteArrOut.toString(network.socket.Constant.defaultCharset));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.closeSocket();
		}

	}
}

package network.socket.longConnection.example.client;

import java.io.InputStream;
import network.socket.longConnection.example.message.Constant;
import network.socket.longConnection.example.exception.UnExpectedMessageException;

public class ClientDataGetter implements Runnable {

	private Client client;

	public ClientDataGetter(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			while (!client.isClose()) {
				InputStream input = client.getSocket().getInputStream();
				byte type = (byte) input.read();
				if (type != -1) {
					ClientDataProcesor clientDataProcesor = null;
					if (Constant.msgTypeNormal == type) {
						clientDataProcesor = new ClientMessageDataProcesor(client);
					} else if (Constant.msgTypeHeartBeatAck == type) {
						clientDataProcesor = new ClientHeartBeatAckDataProcesor(client);
					} else {
						throw new UnExpectedMessageException("unexpected message from server");
					}
					if (clientDataProcesor != null) {
						clientDataProcesor.Process();
					}
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.closeSocket();
		}

	}
}

package network.socket.longConnection.example.server;

import java.io.InputStream;
import network.socket.longConnection.example.message.Constant;
import network.socket.longConnection.example.exception.UnExpectedMessageException;

public class ServerDataGetter implements Runnable {

	private Server server;

	private ServerDataSender serverDataSender;

	public ServerDataGetter(Server server, ServerDataSender serverDataSender) {
		this.server = server;
		this.serverDataSender = serverDataSender;
	}

	@Override
	public void run() {
		try {
			while (!server.isClose()) {
				InputStream input = server.getSocket().getInputStream();
				byte type = (byte) input.read();
				if (type != -1) {
					ServerDataProcesor serverDataProcesor = null;
					if (Constant.msgTypeNormal == type) {
						serverDataProcesor = new ServerMessageDataProcesor(server);
					} else if (Constant.msgTypeHeartBeat == type) {
						serverDataProcesor = new ServerHeartBeatDataProcesor(server, serverDataSender);
					} else {
						throw new UnExpectedMessageException("unexpected message from client");
					}
					if (serverDataProcesor != null) {
						serverDataProcesor.Process();
					}
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.closeSocket();
		}

	}
}

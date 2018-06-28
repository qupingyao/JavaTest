package network.socket.longConnection.example.server;

import network.socket.longConnection.example.message.HeartBeatAckMessage;
import network.socket.longConnection.example.message.Message;

public class ServerHeartBeatDataProcesor implements ServerDataProcesor {

	private Server server;

	private ServerDataSender serverDataSender;

	public ServerHeartBeatDataProcesor(Server server, ServerDataSender serverDataSender) {
		this.server = server;
		this.serverDataSender = serverDataSender;
	}

	@Override
	public void Process() {
		if (!server.isClose()) {
			server.setLastHeartBeatDataRevTime(System.currentTimeMillis());
			Message message = new HeartBeatAckMessage();
			serverDataSender.sendData(message.toByteArr());
		}
	}

}

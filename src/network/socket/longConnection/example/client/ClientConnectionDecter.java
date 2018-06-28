package network.socket.longConnection.example.client;

import network.socket.longConnection.example.exception.SocketConnectionException;

public class ClientConnectionDecter implements Runnable {

	private Client client;

	public ClientConnectionDecter(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		try {
			client.setLastHeartBeatAckDataRevTime(System.currentTimeMillis());
			while (!client.isClose()) {
				if (System.currentTimeMillis()
						- client.getLastHeartBeatAckDataRevTime() > Client.maxHeartBeatAckRevIntervalSeconds * 1000) {
					throw new SocketConnectionException("server connect error");
				}
				Thread.sleep(Client.heartBeatAckRevSleepSeconds * 1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.closeSocket();
		}
	}

}

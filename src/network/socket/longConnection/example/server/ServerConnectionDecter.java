package network.socket.longConnection.example.server;

import network.socket.longConnection.example.exception.SocketConnectionException;

public class ServerConnectionDecter implements Runnable {

	private Server server;

	public ServerConnectionDecter(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
			server.setLastHeartBeatDataRevTime(System.currentTimeMillis());
			while (!server.isClose()) {
				if (System.currentTimeMillis()
						- server.getLastHeartBeatDataRevTime() > Server.maxHeartBeatRevIntervalSeconds * 1000) {
					throw new SocketConnectionException("client connect error");
				}
				Thread.sleep(Server.heartBeatRevSleepSeconds * 1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.closeSocket();
		}
	}

}

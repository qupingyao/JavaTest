package network.socket.longConnection.example.client;

import network.socket.longConnection.example.message.HeartBeatMessage;
import network.socket.longConnection.example.message.Message;

public class ClientHeartBeatSender implements Runnable {

	private Client client;

	private ClientDataSender clientDataSender;

	public ClientHeartBeatSender(Client client, ClientDataSender clientDataSender) {
		this.client = client;
		this.clientDataSender = clientDataSender;
	}

	@Override
	public void run() {
		try {
			client.setLastHeartBeatDataSendTime(System.currentTimeMillis());
			while (!client.isClose()) {
				if (System.currentTimeMillis()
						- client.getLastHeartBeatDataSendTime() > Client.maxHeartBeatSendIntervalSeconds * 1000) {
					client.setLastHeartBeatDataSendTime(System.currentTimeMillis());
					Message message = new HeartBeatMessage();
					clientDataSender.sendData(message.toByteArr());
				}
				Thread.sleep(Client.heartBeatSendSleepSeconds * 1000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.closeSocket();
		}
	}

}

package network.socket.longConnection.example.client;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;
import network.socket.longConnection.example.message.Message;
import network.socket.longConnection.example.message.NorMalMessage;
import network.socket.longConnection.example.server.ServerCenter;

public class Client {

	public static final int maxHeartBeatSendIntervalSeconds = 10;

	public static final int heartBeatSendSleepSeconds = 5;

	public static final int maxHeartBeatAckRevIntervalSeconds = 30;

	public static final int heartBeatAckRevSleepSeconds = 5;

	private volatile boolean isClose;

	private volatile long lastHeartBeatDataSendTime;

	private volatile long lastHeartBeatAckDataRevTime;

	private volatile Socket socket = new Socket();

	public void start() {
		Scanner scanner = new Scanner(System.in);
		try {
			socket.connect(new InetSocketAddress(ServerCenter.serverHost, ServerCenter.serverPort));
			System.out.println("client login success");
			ClientDataSender clientDataSender = new ClientDataSender(this);
//			new Thread(new ClientHeartBeatSender(this, clientDataSender)).start();
//			new Thread(new ClientConnectionDecter(this)).start();
			new Thread(new ClientDataGetter(this)).start();
			while (!isClose) {
				String line = scanner.nextLine();
				Message message = new NorMalMessage(line.getBytes(network.socket.Constant.defaultCharset));
				clientDataSender.sendData(message.toByteArr());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.closeSocket();
		}
	}

	public boolean isClose() {
		return isClose;
	}

	public void setClose(boolean isClose) {
		this.isClose = isClose;
	}

	public long getLastHeartBeatDataSendTime() {
		return lastHeartBeatDataSendTime;
	}

	public void setLastHeartBeatDataSendTime(long lastHeartBeatDataSendTime) {
		this.lastHeartBeatDataSendTime = lastHeartBeatDataSendTime;
	}

	public long getLastHeartBeatAckDataRevTime() {
		return lastHeartBeatAckDataRevTime;
	}

	public void setLastHeartBeatAckDataRevTime(long lastHeartBeatAckDataRevTime) {
		this.lastHeartBeatAckDataRevTime = lastHeartBeatAckDataRevTime;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void closeSocket() {
		if (!isClose) {
			try {
				isClose = true;
				socket.close();
				System.out.println("server close");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

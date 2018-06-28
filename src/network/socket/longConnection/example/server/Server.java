package network.socket.longConnection.example.server;

import java.net.Socket;
import java.util.Scanner;
import network.socket.longConnection.example.message.Message;
import network.socket.longConnection.example.message.NorMalMessage;

public class Server implements Runnable {

	public static final int maxHeartBeatRevIntervalSeconds = 30;

	public static final int heartBeatRevSleepSeconds = 5;

	private volatile Socket socket;

	private volatile boolean isClose;

	private volatile long lastHeartBeatDataRevTime;

	public Server(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("server login success");
			ServerDataSender serverDataSender = new ServerDataSender(this);
//			new Thread(new ServerConnectionDecter(this)).start();
			new Thread(new ServerDataGetter(this, serverDataSender)).start();
			while (!isClose) {
				String line = scanner.nextLine();
				Message message = new NorMalMessage(line.getBytes(network.socket.Constant.defaultCharset));
				serverDataSender.sendData(message.toByteArr());
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

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public boolean isClose() {
		return isClose;
	}

	public void setClose(boolean isClose) {
		this.isClose = isClose;
	}

	public long getLastHeartBeatDataRevTime() {
		return lastHeartBeatDataRevTime;
	}

	public void setLastHeartBeatDataRevTime(long lastHeartBeatDataRevTime) {
		this.lastHeartBeatDataRevTime = lastHeartBeatDataRevTime;
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

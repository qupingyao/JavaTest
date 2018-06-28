package network.socket.testInputStream.example3;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2 {

	private static final String serverHost = "127.0.0.1";

	private static final int serverPort = 8080;

	private static volatile boolean isServerContinue = false;

	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(serverPort);
			new Thread(new Runnable() {
				@Override
				public void run() {
					Socket socket = new Socket();
					try {
						socket.connect(new InetSocketAddress(serverHost, serverPort));
						InputStream input = socket.getInputStream();
						isServerContinue = true;
						System.out.println("client create objectInputStream start, time:"
								+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						ObjectInputStream objectInputStream = new ObjectInputStream(input);
						System.out.println("client create objectInputStream success, time:"
								+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							socket.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			while (true) {
				final Socket socket = server.accept();
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							OutputStream output = socket.getOutputStream();
							while (!isServerContinue) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							try {
								Thread.sleep(5000);
							} catch (Exception e) {
								e.printStackTrace();
							}
							ObjectOutputStream outputStream = new ObjectOutputStream(output);
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
								socket.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				try {
					server.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

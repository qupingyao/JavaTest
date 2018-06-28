package network.socket.testInputStream.example1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import network.socket.Constant;

public class Test {

	private static final String serverHost = "127.0.0.1";

	private static final int serverPort = 8080;

	private static final String serverData = "hello";

	private static volatile int readByteTimes = 0;

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
						while (true) {
							readByteTimes++;
							System.out.println("client get byte(int value):" + input.read() + ", time:"
									+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
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
							output.write(serverData.getBytes(Constant.defaultCharset));
							while (readByteTimes < serverData.getBytes(Constant.defaultCharset).length) {
							}
							try {
								Thread.sleep(5000);
							} catch (Exception e) {
								e.printStackTrace();
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
								socket.close();
								System.out.println("server close, time:"
										+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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

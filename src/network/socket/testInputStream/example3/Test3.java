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

public class Test3 {

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
						ObjectInputStream objectInputStream = new ObjectInputStream(input);
						isServerContinue = true;
						System.out.println("client start readObject success, time:"
								+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
						Object object = objectInputStream.readObject();
						System.out.println("client readObject success, time:"
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
							ObjectOutputStream outputStream = new ObjectOutputStream(output);
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
							outputStream.writeObject(new String("hello"));
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

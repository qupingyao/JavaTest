package network.socket.testBackLog;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import network.socket.Constant;

public class TestBackLog {

	private static final String serverHost = "127.0.0.1";

	private static final int serverPort = 8080;

	private static final String serverData = "I'm server";

	private static final String clientData = "I'm client";

	private static final int backlog = 2;

	private static final int clientThreadNum = 5;

	private static AtomicInteger currentPreparedNum = new AtomicInteger();

	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(serverPort, backlog);
			for (int i = 0; i < clientThreadNum; i++) {
				final int id = i;
				new Thread(new Runnable() {
					@Override
					public void run() {
						Socket socket = new Socket();
						ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
						try {
							socket.connect(new InetSocketAddress(serverHost, serverPort));
							System.out.println("client-" + id + " connect success");
							OutputStream output = socket.getOutputStream();
							InputStream input = socket.getInputStream();
							output.write(clientData.getBytes(Constant.defaultCharset));
							System.out.println("client-" + id + " write success");
							currentPreparedNum.getAndIncrement();
							int n;
							while ((n = input.read()) != -1) {
								byteArrOut.write(n);
							}
							System.out.println("client-" + id + " get data success:"
									+ byteArrOut.toString(Constant.defaultCharset));
							while (true) {
								try {
									Thread.sleep(10000);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} catch (Exception e) {
							System.err.println("client-" + id + " socket error");
						} finally {
							try {
								byteArrOut.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							try {
								socket.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
			while (currentPreparedNum.get() < clientThreadNum) {
			}
			System.out.println("server start listen");
			while (true) {
				final Socket socket = server.accept();
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							OutputStream output = socket.getOutputStream();
							output.write(serverData.getBytes(Constant.defaultCharset));
							System.out.println("server write data success");
							while (true) {
								try {
									Thread.sleep(10000);
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

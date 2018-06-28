package network.socket.testInputStream.example3;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import network.socket.CommonTools;

public class Test1 {

	private static final String serverHost = "127.0.0.1";

	private static final int serverPort = 8080;

	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(serverPort);
			new Thread(new Runnable() {
				@Override
				public void run() {
					Socket socket = new Socket();
					ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
					try {
						socket.connect(new InetSocketAddress(serverHost, serverPort));
						InputStream input = socket.getInputStream();
						int n;
						while ((n = input.read()) != -1) {
							byteArrOut.write(n);
						}
						System.out.println(
								"client get data(byte): " + CommonTools.getByteArrStr(byteArrOut.toByteArray()));
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

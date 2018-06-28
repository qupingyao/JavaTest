package network.socket.longConnection.test;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import network.socket.Constant;

public class Server {

	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(8080);
			while (true) {
				final Socket socket = server.accept();
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							System.out.println("服务端开始");
							OutputStream output = socket.getOutputStream();
							for(int i=0;i<10;i++){
								output.write("hello".getBytes("UTF-8"));
							}
							try {
								Thread.sleep(500000);
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
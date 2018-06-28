package network.socket.test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import network.socket.test.A;

public class Server {
	
	private final static String filePath = "src/network/socket/test/serializable.txt";
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
//			serverSocket.
			serverSocket = new ServerSocket(8080);
//			serverSocket.bind(new InetSocketAddress("127.0.0.1", 8080));
			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(new Runnable() {
					@Override
					public void run() {
						OutputStream outputStream = null;
						ObjectOutputStream objectOutputStream = null;
						try{
							outputStream = socket.getOutputStream();
//							objectOutputStream = new ObjectOutputStream(outputStream);
//							A a1 = new A(10);
//							A a2 = new A(11);
//							A a3 = new A(12);
							outputStream.write("hello".getBytes("UTF-8"));
							outputStream.write("hello".getBytes("UTF-8"));
//							objectOutputStream.writeObject(a1);
//							objectOutputStream.writeObject(a2);
//							objectOutputStream.writeObject(a3);
//							socket.shutdownOutput();
//							while(true){
//								try{
//									Thread.sleep(10000);
//								}catch (Exception e) {
//									e.printStackTrace();
//								}
//							}
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							if(socket!=null){
								try{
//									socket.close();
									System.out.println("server socket end" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
								}catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
}

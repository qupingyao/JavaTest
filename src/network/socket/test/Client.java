package network.socket.test;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
	
	public static void main(String[] args) {
		Socket socket = new Socket();
		InputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		StringBuffer buffer = new StringBuffer();
		try{
			socket.connect(new InetSocketAddress("127.0.0.1", 8080));
			inputStream = socket.getInputStream();
//			objectInputStream = new ObjectInputStream(inputStream);
			Thread.sleep(3000);
//			A a1 = (A)objectInputStream.readObject();
//			System.out.println(a1.getNum() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			A a2 = (A)objectInputStream.readObject();
//			System.out.println(a2.getNum() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			Thread.sleep(5000);
//			A a3 = (A)objectInputStream.readObject();
//			System.out.println(a3.getNum() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			A a4 = (A)objectInputStream.readObject();
//			System.out.println(a4.getNum() + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			int n;
			while ((n = inputStream.read()) != -1) {
				buffer.append(String.format("%-3s", Integer.toHexString(n)));
				System.out.println(String.format("%-3s", Integer.toHexString(n)));
			}
			System.out.println("client:"+buffer.toString());
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				socket.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

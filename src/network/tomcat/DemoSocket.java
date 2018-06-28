package network.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class DemoSocket {

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 8080);
			InputStream inputStream = socket.getInputStream();

			// 输出IO流
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream), true);
			pw.println("Hello Tomcat!");

			StringBuffer requestResult = new StringBuffer();
			byte[] bytes = new byte[1024];
			while (inputStream.read(bytes) != -1) {
				String s = new String(bytes);
				System.out.println(s);
				requestResult.append(s);
			}
			System.out.println(requestResult);
			Thread.currentThread().sleep(50);

			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

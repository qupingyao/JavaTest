package network.webService.client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SocketUtils2 {

	public static String doSocket(String reqUrl, String content, String charset) {
		Socket socket = new Socket();
		String finalResult = new String();
		ByteArrayOutputStream byteArrOut1 = new ByteArrayOutputStream();
		int i = 0;
		try {
			URL url = new URL(reqUrl);
			socket.connect(new InetSocketAddress(url.getHost(), url.getPort()));
			OutputStream output = socket.getOutputStream();
			InputStream input = socket.getInputStream();
//			for(int j=0;j<10;j++){
//			try{
//				socket.sendUrgentData(0xFF);
//				System.out.println("没断开"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			}catch (Exception e) {
//				System.out.println("断开了"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//			}
				output.write(content.getBytes(charset));
//				try{
//					socket.sendUrgentData(0xFF);
//					System.out.println("没断开"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//				}catch (Exception e) {
//					System.out.println("断开了"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//				}
				Thread.sleep(1000);
//				try{
//					socket.sendUrgentData(0xFF);
//					System.out.println("没断开"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//				}catch (Exception e) {
//					System.out.println("断开了"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//				}
				output.write(content.getBytes(charset));
//				try{
//					socket.sendUrgentData(0xFF);
//					System.out.println("没断开"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//				}catch (Exception e) {
//					System.out.println("断开了"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//				}
//				i++;
				Thread.sleep(10000);
//			}
//			output.write("aaa".getBytes(charset));
//			socket.sendUrgentData(0xff);
			int n;
			while ((n = input.read()) != -1) {
				System.out.println(n);
				byteArrOut1.write(n);
//				System.out.println("byte:"+Integer.toHexString(n)+" time:"+getCurrentTime());
			}
//			Thread.sleep(10000);
//			output.write(content.getBytes(charset));
			
			System.out.println(getCurrentTime());
//			System.out.println("original http request content:\r\n" + byteArrOut1.toString(charset));
			finalResult = byteArrOut1.toString(charset);
		} catch (Exception e) {
			System.err.println(i);
			e.printStackTrace();
		} finally {
			try {
				byteArrOut1.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return finalResult;
	}
	
	public static String getCurrentTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}

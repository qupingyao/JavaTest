package network.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	
	private static final String data = new StringBuffer()
			.append("<html>")
			.append("<body>")
			.append("<h1>")
			.append("this is homePage")
			.append("</h1>")
			.append("</body>")
			.append("</html>").toString();
	
	private static final String content = new StringBuffer()
			.append("HTTP/1.1 200 OK\r\n")
			.append("Content-Type: text/html\r\n")
			.append("Content-Length: ").append(data.length()).append("\r\n")
			.append("\r\n")
			.append(data).toString();
	
	public static void start(int port) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			while (true) {
				final Socket socket = server.accept();
				System.out.println("server accept");
				new Thread(new Runnable() {
					@Override
					public void run() {
						ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
						try {
							InputStream input = socket.getInputStream();
							OutputStream output = socket.getOutputStream();
							int n = 0;
							while((n = input.read())!=-1){
								byteArrOut.write(n);
								if(byteArrOut.toString().endsWith("\r\n\r\n")){
									break;
								}
							}
							output.write(content.getBytes("UTF-8"));
							while(true){
								try{
									Thread.sleep(10000);
								}catch (Exception e) {
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
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(server!=null){
				try{
					server.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}

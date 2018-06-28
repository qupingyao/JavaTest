package network.http.example;

import java.net.*;
import java.io.*;

public class MyWebServer {
	
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(8000);
			System.out.println("MyWebServer等待来自浏览器的连接\n");
			while (true) {
				final Socket socket = server.accept();
				new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("开始");
						OutputStream output = null;
						ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
						PrintStream out = null;
						try {    
							output = socket.getOutputStream();
						    out = new PrintStream(output);    
						    socket.setSoTimeout(10);    
							int n;
						    out.println("HTTP/1.1 200 OK");    
						    out.println("Content-Type:text/html;charset:UTF-8");    
						    out.println();    
						    out.println("<html><body><h1> this is home page</h1</body></html>");   
						    out.flush();    
						    out.close();    
						} catch (Exception e) {    
						      e.printStackTrace();    
						} finally{
							try{
								socket.close();	
							}catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				});
				
			}
		} catch (Exception e) {
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

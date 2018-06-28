package network.tomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DemoServer {

	private final static String REQUEST_SUCCESS = "HTTP/1.1 200 OK\r\n" + "Content-Type: text/html\r\n" + "\r\n"
			+ "请求成功";

	public static void main(String[] args) {
        try {
            //创建SocketServer
            ServerSocket serverSocket = new ServerSocket(8080, 1, InetAddress.getByName("localhost"));
            boolean stop = false;
            while(!stop) {
                //获取Socket
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                //读取请求数据
                StringBuffer requestInfo = new StringBuffer();
                byte[] bytes = new byte[1024];
                if(inputStream.read(bytes) != -1) {
                    String s = new String(bytes);
                    System.out.println(s);
                    requestInfo.append(s);
                }
                System.out.println("Request Info:" + requestInfo);

                //输出结果数据
                outputStream.write(REQUEST_SUCCESS.getBytes());

                socket.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
        
}

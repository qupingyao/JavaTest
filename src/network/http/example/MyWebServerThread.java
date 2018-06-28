package network.http.example;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MyWebServerThread extends Thread {    
	  private Socket socket;    
	    
	  MyWebServerThread(Socket socket) {    
	    this.socket = socket;    
	  }    
	    
	  @Override    
	  public void run() {    
	    try {    
	      InputStreamReader is = new InputStreamReader(socket.getInputStream());    
	      char[] bs = new char[2048];    
	      PrintStream out;    
	      out = new PrintStream(socket.getOutputStream());    
	      StringBuilder msg = new StringBuilder();    
	      // 如果10毫秒还没有数据，则视同没有新的数据了。    
	      // 因为有Keep-Alive的缘故，浏览器可能不主动断开连接的。    
	      // 实际应用，会根据协议第一行是GET还是 POST确定。    
	      socket.setSoTimeout(10);    
	      //    
	      // 此处读入请求数据并做相应的处理    
	      //    
	      int len = -1;    
	      try {    
	        while ((len = is.read(bs)) != -1) {    
	          msg.append(bs, 0, len);    
	          msg.append("\n");    
	        }    
	      } catch (Exception ex) {    
	        // ex.printStackTrace();    
	      }    
	      // 下面是由服务器直接生成的主页内容    
	      // 1、首先向浏览器输出响应头信息    
	      out.println("HTTP/1.1 200 OK");    
	      out.println("Content-Type:text/html;charset:GBK");    
	      out.println();    
	      // 2、输出主页信息    
	      out    
	          .println("<HTML><BODY>"    
	              + "<center>"    
	              + "<H1>HTTP协议测试服务器,当前时间："    
	              + new java.util.Date()    
	              + "</h1>"    
	              + "<form method='get'>username:<input type='text' name='username'/>password:<input type='text' name='password'/><input type='submit' value='GET测试'/></form><br/>"    
	              + "<form method='post'>username:<input type='text' name='username'/>password:<input type='text' name='password'/><input type='submit' value='POST测试'/></form><br/>"    
	              + "<form method='post'  enctype='multipart/form-data'>phototitle:<input type='text' name='phototitle'/>photo:<input type='file' name='photo'/><input type='submit' value='Upload测试'/></form>"    
	              + "</center>您提交的数据如下:<pre>" + msg.toString() + "</pre></BODY></HTML>");    
	      out.flush();    
	      out.close();    
	      is.close();    
	      System.out.println("close");    
	      // 关闭连接    
	      socket.close();    
	    } catch (IOException e) {    
	      e.printStackTrace();    
	    }    
	  }    
	}   

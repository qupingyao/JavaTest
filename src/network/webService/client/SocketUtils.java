package network.webService.client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import org.apache.commons.lang.StringUtils;

public class SocketUtils {

	private static final String chunkedModeStr = "Transfer-encoding: chunked";

	public static String doSocket(String reqUrl, String content, String charset) {
		Socket socket = new Socket();
		String finalResult = new String();
		ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
		try {
			URL url = new URL(reqUrl);
			socket.connect(new InetSocketAddress(url.getHost(), url.getPort()));
			OutputStream output = socket.getOutputStream();
			InputStream input = socket.getInputStream();
			output.write(content.getBytes(charset));
			int n;
			while ((n = input.read()) != -1) {
				byteArrOut.write(n);
			}
			String originalResult = byteArrOut.toString(charset);
			System.out.println("original http request content:\r\n" + originalResult);
			if (!StringUtils.isEmpty(originalResult) && originalResult.contains(chunkedModeStr)) {
				finalResult = HttpChunkedDataUtils.decode(originalResult, charset);
			} else {
				finalResult = originalResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				byteArrOut.close();
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
}

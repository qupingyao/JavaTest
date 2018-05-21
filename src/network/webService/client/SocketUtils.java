package network.webService.client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;

public class SocketUtils {

	private static final String chunkedModeStr = "Transfer-encoding: chunked";

	public static String doSocket(String reqUrl, String content, String charset) {
		Socket socket = null;
		String finalResult = new String();
		InputStream input = null;
		OutputStream output = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			URL url = new URL(reqUrl);
			socket = new Socket(url.getHost(), url.getPort());
			output = socket.getOutputStream();
			input = socket.getInputStream();
			output.write(content.getBytes(charset));
			output.flush();
			byte[] byteArr = new byte[4096];
			int n;
			while ((n = input.read(byteArr)) != -1) {
				byteArrayOutputStream.write(byteArr, 0, n);
			}
			String originalResult = byteArrayOutputStream.toString(charset);
			System.out.println("original http request content:\r\n" + originalResult);
			if (!StringUtils.isEmpty(originalResult) && originalResult.contains(chunkedModeStr)) {
				finalResult = HttpChunkedDataUtils.decode(originalResult, charset);
			} else {
				finalResult = originalResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return finalResult;
	}
}

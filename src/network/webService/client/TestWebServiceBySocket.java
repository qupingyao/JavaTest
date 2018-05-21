package network.webService.client;

import java.net.URL;
import network.webService.server.WebServiceServer;

public class TestWebServiceBySocket {

	public static void main(String[] args) {
		try {
			URL url = new URL(WebServiceServer.webServiceUrl);
			int length = Constant.content.getBytes(Constant.defaultCharset).length;
			String httpHeader = getHttpPostHeader(url, length);
			String message = new StringBuffer().append(httpHeader).append(Constant.content).toString();
			System.out.println("original http request content after analysis:\r\n"
					+ SocketUtils.doSocket(WebServiceServer.webServiceUrl, message, Constant.defaultCharset));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getHttpPostHeader(URL url, int contentLength) {
		StringBuffer header = new StringBuffer().append("POST ").append(url.getPath()).append(" HTTP/1.1\r\n")
				.append("Accept-Encoding: gzip,deflate\r\n").append("Content-Type: text/xml;charset=UTF-8\r\n")
				.append("SOAPAction: \"\"\r\n").append("User-Agent: Jakarta Commons-HttpClient/3.1\r\n").append("Host:")
				.append(url.getHost()).append(":").append(url.getPort()).append("\r\n")
				.append("Content-Length:" + contentLength + "\r\n").append("\r\n");
		return header.toString();
	}

}
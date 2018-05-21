package network.webService.client;

import network.webService.server.WebServiceServer;

public class TestWebServiceByHttpRequest {

	public static void main(String[] args) {
		System.out.println(
				HttpUtils.doXmlPost(WebServiceServer.webServiceUrl, Constant.content, Constant.defaultCharset));
	}

}

package network.webService.server;

import javax.xml.ws.Endpoint;

public class WebServiceServer {

	public static final String webServiceUrl = "http://127.0.0.1:8081/webservicetest/index";

	public static void publish() {
		Endpoint.publish(WebServiceServer.webServiceUrl, new A());
	}

}
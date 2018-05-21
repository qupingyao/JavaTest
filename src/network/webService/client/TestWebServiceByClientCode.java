package network.webService.client;

import java.net.URL;
import java.util.Map;
import javax.xml.ws.BindingProvider;
import network.webService.clientCode.A;
import network.webService.clientCode.AService;
import network.webService.clientCode.MyObject;
import network.webService.server.WebServiceServer;

public class TestWebServiceByClientCode {

	private static final int requestTimeOutSeconds = 3000;

	public static void main(String[] args) {
		try {
			MyObject myObject = new MyObject();
			myObject.setId(1);
			myObject.setName("Tom");
			URL url = new URL(WebServiceServer.webServiceUrl);
			AService aService = new AService(url);
			A a = aService.getAPort();
			BindingProvider bindingProvider = (BindingProvider) a;
			Map<String, Object> requestContext = bindingProvider.getRequestContext();
			requestContext.put("com.sun.xml.internal.ws.request.timeout", requestTimeOutSeconds);
			System.out.println(a.say(myObject));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
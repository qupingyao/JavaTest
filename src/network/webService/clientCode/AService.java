package network.webService.clientCode;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "AService", targetNamespace = "http://server.webService.network/", wsdlLocation = "http://127.0.0.1:8080/webservicetest/index?wsdl")
public class AService extends Service {

	private final static URL ASERVICE_WSDL_LOCATION;
	private final static WebServiceException ASERVICE_EXCEPTION;
	private final static QName ASERVICE_QNAME = new QName("http://server.webService.network/", "AService");

	static {
		URL url = null;
		WebServiceException e = null;
		try {
			url = new URL("http://127.0.0.1:8080/webservicetest/index?wsdl");
		} catch (MalformedURLException ex) {
			e = new WebServiceException(ex);
		}
		ASERVICE_WSDL_LOCATION = url;
		ASERVICE_EXCEPTION = e;
	}

	public AService() {
		super(__getWsdlLocation(), ASERVICE_QNAME);
	}

	public AService(WebServiceFeature... features) {
		super(__getWsdlLocation(), ASERVICE_QNAME, features);
	}

	public AService(URL wsdlLocation) {
		super(wsdlLocation, ASERVICE_QNAME);
	}

	public AService(URL wsdlLocation, WebServiceFeature... features) {
		super(wsdlLocation, ASERVICE_QNAME, features);
	}

	public AService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public AService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
		super(wsdlLocation, serviceName, features);
	}

	@WebEndpoint(name = "APort")
	public A getAPort() {
		return super.getPort(new QName("http://server.webService.network/", "APort"), A.class);
	}

	@WebEndpoint(name = "APort")
	public A getAPort(WebServiceFeature... features) {
		return super.getPort(new QName("http://server.webService.network/", "APort"), A.class, features);
	}

	private static URL __getWsdlLocation() {
		if (ASERVICE_EXCEPTION != null) {
			throw ASERVICE_EXCEPTION;
		}
		return ASERVICE_WSDL_LOCATION;
	}

}

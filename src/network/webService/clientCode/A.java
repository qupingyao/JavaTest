package network.webService.clientCode;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;

@WebService(name = "A", targetNamespace = "http://server.webService.network/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({ ObjectFactory.class })
public interface A {

	@WebMethod
	@WebResult(partName = "return")
	@Action(input = "http://server.webService.network/A/sayRequest", output = "http://server.webService.network/A/sayResponse")
	public String say(@WebParam(name = "arg0", partName = "arg0") MyObject arg0);

}

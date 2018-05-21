package network.webService.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class A {

	@WebMethod(operationName = "say")
	public String say(MyObject ob) {
		System.out.println("I say:" + ob.getName());
		return ob.getName();
	}

}
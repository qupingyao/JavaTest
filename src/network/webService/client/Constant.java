package network.webService.client;

public class Constant {

	public static final String content = new StringBuffer()
			.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://server.webService.network/\">")
			.append("<soapenv:Header/>").append("<soapenv:Body>").append("<ser:say>").append("<arg0>")
			.append("<id>1</id>").append("<name>Tom</name>").append("</arg0>").append("</ser:say>")
			.append("</soapenv:Body>").append("</soapenv:Envelope>").toString();

	public static final String defaultCharset = "UTF-8";
}

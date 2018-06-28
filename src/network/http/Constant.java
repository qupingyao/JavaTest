package network.http;

public class Constant {

	public static final String defaultCharset = "UTF-8";
	
	public static final String speSplit = "\r\n";
	
	public static void main(String[] args)throws Exception {
		System.out.println(CommonTools.getByteArrStr(speSplit.getBytes("UTF-8")));
	}

}

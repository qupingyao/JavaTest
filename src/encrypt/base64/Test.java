package encrypt.base64;

import encrypt.CommonTools;
import encrypt.Constant;

public class Test {

	private static final String text = "我是你爸爸,I'm your father";

	public static void main(String[] args) {
		try {
			byte[] sourceArr = text.getBytes(Constant.defaultCharset);
			System.out.println("source content(char): " + text);
			System.out.println("source content(byte): " + CommonTools.getByteArrStr(sourceArr));
			byte[] encodeArr = Base64Tools.encode(sourceArr);
			System.out.println("after base64 encode(char): " + new String(encodeArr, Constant.defaultCharset));
			System.out.println("after base64 encode(byte): " + CommonTools.getByteArrStr(encodeArr));
			byte[] decodeArr = Base64Tools.decode(encodeArr);
			System.out.println("after base64 decode(char): " + new String(decodeArr, Constant.defaultCharset));
			System.out.println("after base64 decode(byte): " + CommonTools.getByteArrStr(decodeArr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
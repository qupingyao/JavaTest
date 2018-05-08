package encrypt.base64;

import encrypt.CommonTools;

public class Test {

	private static final String text = "我是你爸爸,I'm your father";

	public static void main(String[] args) {
		try {
			byte[] sourceArr = text.getBytes("UTF-8");
			System.out.println("source content(char): " + text);
			System.out.println("source content(byte): " + CommonTools.printByteArr(sourceArr));
			byte[] encodeArr = Base64Tools.encode(sourceArr);
			System.out.println("after base64 encode(char): " + new String(encodeArr, "UTF-8"));
			System.out.println("after base64 encode(byte): " + CommonTools.printByteArr(encodeArr));
			byte[] decodeArr = Base64Tools.decode(encodeArr);
			System.out.println("after base64 decode(char): " + new String(decodeArr, "UTF-8"));
			System.out.println("after base64 decode(byte): " + CommonTools.printByteArr(decodeArr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
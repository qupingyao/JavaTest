package encrypt.des;

import encrypt.CommonTools;

public class Test {

	private static final String key = "abcdefgh";

	private static final String iv = "qwertyui";

	private static final String mode = "DES/CBC/PKCS5Padding";

	private static final String text = "我是你爸爸,I'm your father";

	public static void main(String[] args) {
		try {
			byte[] keyArr = key.getBytes("UTF-8");
			byte[] ivArr = iv.getBytes("UTF-8");
			byte[] sourceArr = text.getBytes("UTF-8");
			System.out.println("source content(char): " + text);
			System.out.println("source content(byte): " + CommonTools.printByteArr(sourceArr));
			byte[] encodeArr = DesTools.encode(sourceArr, keyArr, ivArr, mode);
			System.out.println("after des encode(byte): " + CommonTools.printByteArr(encodeArr));
			byte[] decodeArr = DesTools.decode(encodeArr, keyArr, ivArr, mode);
			System.out.println("after des decode(char): " + new String(decodeArr, "UTF-8"));
			System.out.println("after des decode(byte): " + CommonTools.printByteArr(decodeArr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
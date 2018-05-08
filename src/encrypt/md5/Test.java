package encrypt.md5;

import encrypt.CommonTools;

public class Test {

	private static final String text = "我是你爸爸,I'm your father";

	public static void main(String[] args) {
		try {
			byte[] sourceArr = text.getBytes("UTF-8");
			System.out.println("source content(char): " + text);
			System.out.println("source content(byte): " + CommonTools.printByteArr(sourceArr));
			byte[] encode8Arr = Md5Tools.encode8(sourceArr);
			System.out.println("after md5 encode8(byte): " + CommonTools.printByteArr(encode8Arr));
			byte[] encode16Arr = Md5Tools.encode16(sourceArr);
			System.out.println("after md5 encode16(byte): " + CommonTools.printByteArr(encode16Arr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
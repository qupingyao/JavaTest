package encrypt.md5;

import encrypt.CommonTools;
import encrypt.Constant;

public class Test {

	private static final String text = "我是你爸爸,I'm your father";

	public static void main(String[] args) {
		try {
			byte[] sourceArr = text.getBytes(Constant.defaultCharset);
			System.out.println("source content(char): " + text);
			System.out.println("source content(byte): " + CommonTools.getByteArrStr(sourceArr));
			byte[] encode8Arr = Md5Tools.encode8(sourceArr);
			System.out.println("after md5 encode8(byte): " + CommonTools.getByteArrStr(encode8Arr));
			byte[] encode16Arr = Md5Tools.encode16(sourceArr);
			System.out.println("after md5 encode16(byte): " + CommonTools.getByteArrStr(encode16Arr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
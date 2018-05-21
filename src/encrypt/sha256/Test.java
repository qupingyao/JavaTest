package encrypt.sha256;

import encrypt.CommonTools;
import encrypt.Constant;

public class Test {

	private static final String text = "我是你爸爸,I'm your father";

	public static void main(String[] args) {
		try {
			byte[] sourceArr = text.getBytes(Constant.defaultCharset);
			System.out.println("source content(char): " + text);
			System.out.println("source content(byte): " + CommonTools.getByteArrStr(sourceArr));
			byte[] encode8Arr = Sha256Tools.encode(sourceArr);
			System.out.println("after sha256 encode(byte): " + CommonTools.getByteArrStr(encode8Arr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

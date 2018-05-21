package encrypt.threeDes;

import encrypt.CommonTools;
import encrypt.Constant;

public class Test {

	private static final String key = "abcdefgh12345678abcdefgh";

	private static final String iv = "qwertyui";

	private static final String mode = "DESede/CBC/PKCS5Padding";

	private static final String text = "我是你爸爸,I'm your father";

	public static void main(String[] args) {
		try {
			byte[] keyArr = key.getBytes(Constant.defaultCharset);
			byte[] ivArr = iv.getBytes(Constant.defaultCharset);
			byte[] sourceArr = text.getBytes(Constant.defaultCharset);
			System.out.println("source content(char): " + text);
			System.out.println("source content(byte): " + CommonTools.getByteArrStr(sourceArr));
			byte[] encodeArr = ThreeDesTools.encode(sourceArr, keyArr, ivArr, mode);
			System.out.println("after threeDes encode(byte): " + CommonTools.getByteArrStr(encodeArr));
			byte[] decodeArr = ThreeDesTools.decode(encodeArr, keyArr, ivArr, mode);
			System.out.println("after threeDes decode(char): " + new String(decodeArr, Constant.defaultCharset));
			System.out.println("after threeDes decode(byte): " + CommonTools.getByteArrStr(decodeArr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
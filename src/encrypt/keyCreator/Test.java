package encrypt.keyCreator;

import encrypt.CommonTools;
import encrypt.Constant;

public class Test {

	private static final String algorithm = "AES";

	private static final String secureRandomAlgorithm = "SHA1PRNG";

	private static final String seed = "abcdefgh";

	private static final int length = 128;

	public static void main(String[] args) {
		try {
			byte[] seedArr = seed.getBytes(Constant.defaultCharset);
			System.out.println("seed content(char): " + seed);
			System.out.println("seed content(byte): " + CommonTools.getByteArrStr(seedArr));
			byte[] pwdArr = KeyCreator.getPwd(seedArr, length, algorithm, secureRandomAlgorithm);
			System.out.println("key content(byte): " + CommonTools.getByteArrStr(pwdArr));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

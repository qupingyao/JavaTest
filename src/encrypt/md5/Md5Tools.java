package encrypt.md5;

import java.security.MessageDigest;

public class Md5Tools {

	private static final String algorithm = "MD5";

	public static byte[] encode16(byte[] content) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			return md.digest(content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static byte[] encode8(byte[] content) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			byte[] tResult = md.digest(content);
			byte[] result = new byte[8];
			for (int i = 0; i < 8; i++) {
				result[i] = tResult[i + 4];
			}
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}

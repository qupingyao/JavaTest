package encrypt.sha256;

import java.security.MessageDigest;

public class Sha256Tools {

	private static final String algorithm = "SHA-256";

	public static byte[] encode(byte[] content) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			return md.digest(content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
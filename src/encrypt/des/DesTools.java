package encrypt.des;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DesTools {

	private static final String algorithm = "DES";

	public static byte[] encode(byte[] content, byte[] pwd, byte[] iv, String mode) {
		try {
			SecretKey key = new SecretKeySpec(pwd, algorithm);
			Cipher cipher = Cipher.getInstance(mode);
			cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
			return cipher.doFinal(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decode(byte[] content, byte[] pwd, byte[] iv, String mode) {
		try {
			SecretKey key = new SecretKeySpec(pwd, algorithm);
			Cipher cipher = Cipher.getInstance(mode);
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
			return cipher.doFinal(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

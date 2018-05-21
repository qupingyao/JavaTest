package encrypt.keyCreator;

import java.security.SecureRandom;
import javax.crypto.KeyGenerator;

public class KeyCreator {

	public static byte[] getPwd(byte[] seed, int length, String algorithm, String secureRandomAlgorithm) {
		try {
			KeyGenerator kg = KeyGenerator.getInstance(algorithm);
			SecureRandom secureRandom = SecureRandom.getInstance(secureRandomAlgorithm);
			secureRandom.setSeed(seed);
			kg.init(length, secureRandom);
			return kg.generateKey().getEncoded();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

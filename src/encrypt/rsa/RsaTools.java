package encrypt.rsa;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RsaTools {

	private static final String ALGORITHM = "RSA";

	public static KeyPair initKeyPair(int size) {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
			keyPairGenerator.initialize(size);
			return keyPairGenerator.generateKeyPair();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static PublicKey getPublicKey(KeyPair keyPair) {
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
		try {
			KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
			return factory.generatePublic(x509EncodedKeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static PrivateKey getPrivateKey(KeyPair keyPair) {
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyPair.getPrivate().getEncoded());
		try {
			KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
			return factory.generatePrivate(pkcs8EncodedKeySpec);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] encode(byte[] content, Key key, String mode) {
		try {
			Cipher cipher = Cipher.getInstance(mode);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return cipher.doFinal(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] decode(byte[] content, Key key, String mode) {
		try {
			Cipher cipher = Cipher.getInstance(mode);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return cipher.doFinal(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

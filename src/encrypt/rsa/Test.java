package encrypt.rsa;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import encrypt.CommonTools;

public class Test {

	private static final String mode = "RSA/ECB/PKCS1Padding";

	private static final int size = 1024;

	private static final String text = "我是你爸爸,I'm your father";

	public static void main(String[] args) throws Exception {
		KeyPair keyPair = RsaTools.initKeyPair(size);
		PublicKey publicKey = RsaTools.getPublicKey(keyPair);
		PrivateKey privateKey = RsaTools.getPrivateKey(keyPair);
		System.out.println("publickey(byte): " + CommonTools.printByteArr(publicKey.getEncoded()));
		System.out.println("publickey(base64): ");
		CommonTools.printBase64Str(new String(Base64.getEncoder().encode(publicKey.getEncoded()), "UTF-8"));
		System.out.println("private(byte): " + CommonTools.printByteArr(privateKey.getEncoded()));
		System.out.println("private(base64): ");
		CommonTools.printBase64Str(new String(Base64.getEncoder().encode(privateKey.getEncoded()), "UTF-8"));
		byte[] sourceArr = text.getBytes("UTF-8");
		System.out.println("source content(char): " + text);
		System.out.println("source content(byte): " + CommonTools.printByteArr(sourceArr));
		System.out.println();
		byte[] encodeArr1 = RsaTools.encode(sourceArr, privateKey, mode);
		System.out.println("after rsa privatekey encode(byte): " + CommonTools.printByteArr(encodeArr1));
		byte[] decodeArr1 = RsaTools.decode(encodeArr1, publicKey, mode);
		System.out.println("after rsa publickey decode(byte): " + CommonTools.printByteArr(decodeArr1));
		System.out.println("after rsa publickey decode(char): " + new String(decodeArr1, "UTF-8"));
		System.out.println();
		byte[] encodeArr2 = RsaTools.encode(sourceArr, publicKey, mode);
		System.out.println("after rsa publickey encode(byte): " + CommonTools.printByteArr(encodeArr2));
		byte[] decodeArr2 = RsaTools.decode(encodeArr2, privateKey, mode);
		System.out.println("after rsa privatekey decode(byte): " + CommonTools.printByteArr(decodeArr2));
		System.out.println("after rsa privatekey decode(char): " + new String(decodeArr2, "UTF-8"));
	}

}
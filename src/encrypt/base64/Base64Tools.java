package encrypt.base64;

import java.util.Base64;

public class Base64Tools {

	public static byte[] encode(byte[] content) {
		return Base64.getEncoder().encode(content);
	}

	public static byte[] decode(byte[] content) {
		return Base64.getDecoder().decode(content);
	}

}

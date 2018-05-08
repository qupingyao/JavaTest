package encrypt.urlCoder;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlCoderTools {

	public static String encode(String content, String charset) {
		try {
			return URLEncoder.encode(content, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decode(String content, String charset) {
		try {
			return URLDecoder.decode(content, charset);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

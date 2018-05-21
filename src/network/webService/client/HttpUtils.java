package network.webService.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

	private static final String httpMethodPost = "POST";

	private static final String contentTypeXml = "text/xml";

	public static String doXmlPost(String reqUrl, String xml, String charset) {
		StringBuffer buffer = new StringBuffer();
		InputStream in = null;
		InputStreamReader inStreamReader = null;
		BufferedReader bufReader = null;
		OutputStreamWriter outStreamWriter = null;
		HttpURLConnection httpUrlCon = null;
		try {
			URL url = new URL(reqUrl);
			httpUrlCon = (HttpURLConnection) url.openConnection();
			httpUrlCon.setDoOutput(true);
			httpUrlCon.setUseCaches(false);
			httpUrlCon.setRequestMethod(httpMethodPost);
			httpUrlCon.setRequestProperty("Content-Type", contentTypeXml);
			outStreamWriter = new OutputStreamWriter(httpUrlCon.getOutputStream(), charset);
			outStreamWriter.write(new String(xml.getBytes(charset)));
			outStreamWriter.flush();
			in = httpUrlCon.getInputStream();
			inStreamReader = new InputStreamReader(in, charset);
			bufReader = new BufferedReader(inStreamReader);
			String str = null;
			while ((str = bufReader.readLine()) != null) {
				buffer.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outStreamWriter != null) {
				try {
					outStreamWriter.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (httpUrlCon != null) {
				httpUrlCon.disconnect();
			}
		}
		return buffer.toString();
	}

}

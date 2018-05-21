package network.webService.client;

public class HttpChunkedDataUtils {

	private static final String splitStr = "\r\n";

	private static final int hexRadix = 16;

	public static String decode(String httpRequest, String charset) {
		try {
			StringBuffer buffer = new StringBuffer();
			String splitContent = new StringBuffer(splitStr).append(splitStr).toString();
			int dataStartIndex = httpRequest.indexOf(splitContent);
			buffer.append(httpRequest.substring(0, dataStartIndex + splitContent.length()));
			String httpBody = httpRequest.substring(dataStartIndex + splitContent.length());
			int i = httpBody.indexOf(splitStr);
			int blockSize = Integer.parseInt(httpBody.substring(0, i), hexRadix);
			while (blockSize > 0) {
				byte[] restArr = httpBody.substring(i + splitStr.length()).getBytes(charset);
				byte[] temp = new byte[blockSize];
				System.arraycopy(restArr, 0, temp, 0, blockSize);
				buffer.append(new String(temp, charset));
				restArr = subArr(restArr, blockSize + splitStr.getBytes(charset).length, restArr.length);
				httpBody = new String(restArr, charset);
				i = httpBody.indexOf(splitStr);
				blockSize = Integer.parseInt(httpBody.substring(0, i), hexRadix);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return httpRequest;
	}

	private static byte[] subArr(byte[] arr, int start, int end) {
		if (arr != null && arr.length >= end && end > start && start >= 0) {
			byte[] result = new byte[end - start];
			for (int i = 0; i < end - start; i++) {
				result[i] = arr[i + start];
			}
			return result;
		}
		return null;
	}

}

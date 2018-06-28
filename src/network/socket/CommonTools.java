package network.socket;

public class CommonTools {

	private static final String chars = "0123456789abcdef";

	public static String getByteArrStr(byte[] arr) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			buffer.append(String.format("%-1s", chars.charAt(arr[i] >>> 4 & 0x0F)));
			buffer.append(String.format("%-2s", chars.charAt(arr[i] & 0x0F)));
		}
		return buffer.toString();
	}

}
package network.socket.test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public class Reader {
	
	private final static String filePath = "src/network/socket/test/serializable.txt";
	
	public static void main(String[] args) {
		InputStream fileInputStream = null;
		ByteArrayOutputStream byteArrOut = new ByteArrayOutputStream();
		try{
			fileInputStream = new FileInputStream(filePath);
			byte[] byteArr = new byte[4096];
			int n;
			while ((n = fileInputStream.read(byteArr)) != -1) {
				byteArrOut.write(byteArr, 0, n);
			}
			System.out.println(CommonTools.getByteArrStr(byteArrOut.toByteArray()));
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fileInputStream!=null){
				try{
					fileInputStream.close();
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}

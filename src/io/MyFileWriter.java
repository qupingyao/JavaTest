package io;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class MyFileWriter {

	public static void writeTextContentToFile(String filePath,String content,String charset) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(filePath);
			byte[] bArr  = content.getBytes(charset);
			os.write(bArr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(os!=null){
				try{
					os.close();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

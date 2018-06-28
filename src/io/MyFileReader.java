package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class MyFileReader {
	
	public static long getFileSize(String filePath){
		File file = new File(filePath);
		if (file.exists()) {
			return file.length();
		}
		return -1;
	}
	
	public static String readFileByteContent1(String filePath) {
		StringBuffer buffer = new StringBuffer();
		File file = new File(filePath);
		if (file.exists()) {
			InputStream in = null;
			try {
				in = new FileInputStream(file);
				int n;
				while ((n = in.read()) != -1) {
					buffer.append(String.format("%-3s", Integer.toHexString(n)));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return buffer.toString();
	}
	
	public static String readFileByteContent2(String filePath) {
		StringBuffer buffer = new StringBuffer();
		File file = new File(filePath);
		if (file.exists()) {
			InputStream in = null;
			try {
				in = new FileInputStream(file);
				int n;
				byte[] arr = new byte[1024];
				while ((n = in.read(arr)) != -1) {
					for(int i=0;i<n;i++){
						buffer.append(String.format("%-3s", Integer.toHexString(Byte.toUnsignedInt(arr[i]))));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return buffer.toString();
	}
	
	public static String readFileTextContent1(String filePath,String charset) {
		StringBuffer buffer = new StringBuffer();
		File file = new File(filePath);
		if (file.exists()) {
			InputStream in = null;
			Reader reader = null;
			try {
				in = new FileInputStream(file);
				reader = new InputStreamReader(in,charset);
				int tChar;
				while ((tChar = reader.read()) != -1) {
					buffer.append((char) tChar);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return buffer.toString();
	}
	
	public static String readFileTextContent2(String filePath,String charset) {
		StringBuffer buffer = new StringBuffer();
		File file = new File(filePath);
		if (file.exists()) {
			Reader reader = null;
			BufferedReader bufReader = null;
			try {
				reader = new FileReader(file);
				bufReader = new BufferedReader(reader);  
				String line;
				while ((line = bufReader.readLine()) != null) {  
					buffer.append(line);  
		        } 
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bufReader != null) {
					try {
						bufReader.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return buffer.toString();
	}
}

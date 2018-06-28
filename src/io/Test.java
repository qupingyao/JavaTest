package io;

public class Test {

	public static void main(String[] args) throws Exception{
		 String str = new String("这是第一行");
	     byte[] arr = str.getBytes(System.getProperty("file.encoding"));
	     for(byte b:arr){
	    	 System.out.println(Integer.toHexString(Byte.toUnsignedInt(b)));
	     }
	}

}

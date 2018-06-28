package io;

public class TestFileReader {

	private static final String filePath = "src/io/test.txt";

	public static void main(String[] args) {
		System.out.println("fileSize:" + MyFileReader.getFileSize(filePath));
		System.out.println("fileByteContent:" + MyFileReader.readFileByteContent1(filePath));
		System.out.println("fileByteContent:" + MyFileReader.readFileByteContent2(filePath));
		System.out.println("fileTextContent:");
		System.out.println(MyFileReader.readFileTextContent1(filePath, System.getProperty("file.encoding")));
		System.out.println("fileTextContent:");
		System.out.println(MyFileReader.readFileTextContent2(filePath, System.getProperty("file.encoding")));
	}

}
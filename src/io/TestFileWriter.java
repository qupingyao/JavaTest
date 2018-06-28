package io;

public class TestFileWriter {

	private static final String filePath = "src/io/copytest.txt";

	private static final String content = "这是第一行\r\nthis is second line";

	public static void main(String[] args) {
		MyFileWriter.writeTextContentToFile(filePath, content, System.getProperty("file.encoding"));
	}

}
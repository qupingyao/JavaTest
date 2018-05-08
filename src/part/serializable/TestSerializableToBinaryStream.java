package part.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TestSerializableToBinaryStream {

	private final static String filePath = "src/part/serializable/serializable.txt";

	public static void main(String[] args) {
		try {
			A a = new A("john", 23);
			OutputStream fileOutputStream = null;
			ObjectOutputStream objectOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(filePath);
				objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeObject(a);
				objectOutputStream.flush();
			} finally {
				if (objectOutputStream != null) {
					objectOutputStream.close();
				}
			}
			InputStream fileInputStream = null;
			ObjectInputStream objectInputStream = null;
			try {
				fileInputStream = new FileInputStream(filePath);
				objectInputStream = new ObjectInputStream(fileInputStream);
				System.out.println((A) objectInputStream.readObject());
			} finally {
				if (objectInputStream != null) {
					objectInputStream.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

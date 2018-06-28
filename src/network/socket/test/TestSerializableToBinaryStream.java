package network.socket.test;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TestSerializableToBinaryStream {

	private final static String filePath = "src/network/socket/test/serializable.txt";

	public static void main(String[] args) {
		A a = new A(10);
		OutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(filePath);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(a);
			objectOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}

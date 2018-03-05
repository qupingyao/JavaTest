package designPattern.singletonPattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

public class AttackSingletonBySerial {

	private static final String filePath = "D:/temp.txt";

	public static void main(String[] args) {
		try {
			HungrySingleton instance1 = HungrySingleton.getInstance();
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filePath));
			os.writeObject(instance1);
			ObjectInputStream is = new ObjectInputStream(new FileInputStream(filePath));
			HungrySingleton instance2 = (HungrySingleton) is.readObject();
			os.close();
			is.close();
			System.out.println((instance1 == instance2));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

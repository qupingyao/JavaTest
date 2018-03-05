package designPattern.singletonPattern;

public class LazySingleton {

	private static LazySingleton instance;

	private int num;

	private LazySingleton() {
		num = 10;
	}

	public static synchronized LazySingleton getInstance() {
		if (instance == null) {
			instance = new LazySingleton();
		}
		return instance;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
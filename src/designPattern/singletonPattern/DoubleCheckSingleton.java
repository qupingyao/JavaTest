package designPattern.singletonPattern;

public class DoubleCheckSingleton {

	private static volatile DoubleCheckSingleton instance;

	private int num;

	private DoubleCheckSingleton() {
		num = 10;
	}

	public static DoubleCheckSingleton getInstance() {
		if (instance == null) {
			synchronized (DoubleCheckSingleton.class) {
				if (instance == null) {
					instance = new DoubleCheckSingleton();
				}
			}
		}
		return instance;
	}

	public int getNum() {
		return num;
	}

}

package designPattern.singletonPattern;

public class InnerClassSingleton {

	private int num;

	private InnerClassSingleton() {
		num = 10;
	}

	public static InnerClassSingleton getInstance() {
		return Inner.instance;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	private static class Inner {
		private static InnerClassSingleton instance = new InnerClassSingleton();

	}
}

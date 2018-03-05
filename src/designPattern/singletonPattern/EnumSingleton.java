package designPattern.singletonPattern;

public class EnumSingleton {

	private int num;

	private EnumSingleton() {
		num = 10;
	}

	public static EnumSingleton getInstance() {
		return Part.INSTANCE.getInstance();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	private static enum Part {
		INSTANCE;

		private EnumSingleton instance;

		private Part() {
			instance = new EnumSingleton();
		}

		public EnumSingleton getInstance() {
			return instance;
		}
	}
}

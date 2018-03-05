package designPattern.singletonPattern;

import java.io.Serializable;

public class HungrySingleton implements Serializable{

	private static final long serialVersionUID = 1L;

	private static HungrySingleton instance = new HungrySingleton();

	private int num;

	private HungrySingleton() {
		num = 10;
	}

	public static HungrySingleton getInstance() {
		return instance;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
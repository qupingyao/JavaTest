package network.socket.test;

import java.io.Serializable;

public class A implements Serializable {

	private static final long serialVersionUID = 1L;

	private int num;

	public A(int num) {
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
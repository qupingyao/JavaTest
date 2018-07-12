package spring.ioc.ex1;

public class A {

	private int id;

	private B b;

	public void say() {
		System.out.println(id + ":" + b.getCurrentId());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}

}

package Load;

public class A {

	static {
		System.out.println("class A static block");
	}

	private String name;

	public A() {

	}

	public A(String name) {
		this.name = name;
	}

	public void say() {
		System.out.println("I'm a boy");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

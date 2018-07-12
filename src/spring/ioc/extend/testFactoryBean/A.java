package spring.ioc.extend.testFactoryBean;

public class A {

	private int num;

	public A(int num) {
		System.out.println("create class-A instance");
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}

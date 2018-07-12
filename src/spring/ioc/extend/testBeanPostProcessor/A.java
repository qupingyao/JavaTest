package spring.ioc.extend.testBeanPostProcessor;

public class A {

	private int num;

	public A() {
		System.out.println("create class-A instance");
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		System.out.println("set class-A instance property");
		this.num = num;
	}

}

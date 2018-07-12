package spring.ioc.extend.testBeanPostProcessor;

public class B {

	private int num;

	public B() {
		System.out.println("create class-B instance");
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		System.out.println("set class-B instance property");
		this.num = num;
	}

}

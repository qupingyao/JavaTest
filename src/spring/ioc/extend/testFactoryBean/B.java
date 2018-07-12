package spring.ioc.extend.testFactoryBean;

public class B {

	private int num;

	public B(int num) {
		System.out.println("create class-B instance");
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}

package spring.ioc.extend.testFactoryBean;

public class BFactory {

	private int val;

	public BFactory() {
		System.out.println("create class-BFactory instance");
	}

	public B createObj() {
		return new B(val);
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

}

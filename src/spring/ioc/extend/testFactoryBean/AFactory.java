package spring.ioc.extend.testFactoryBean;

import org.springframework.beans.factory.FactoryBean;

public class AFactory implements FactoryBean<A> {

	private int val;

	public AFactory() {
		System.out.println("create class-AFactory instance");
	}

	@Override
	public A getObject() throws Exception {
		return new A(val);
	}

	@Override
	public Class<A> getObjectType() {
		return A.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

}

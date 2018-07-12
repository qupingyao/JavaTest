package spring.ioc.extend.testInitializingBean;

import org.springframework.beans.factory.InitializingBean;

public class A implements InitializingBean{

	private int num;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet()");    
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}

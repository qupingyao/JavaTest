package spring.ioc.ex2;

import org.springframework.beans.factory.DisposableBean;

public class A implements DisposableBean {
	
	private int num;
	
	@Override
	public void destroy() throws Exception {
		System.out.println("class-A instance destroy");
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}

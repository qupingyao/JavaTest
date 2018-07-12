package spring.ioc.extend.testDisposableBean;

import org.springframework.beans.factory.DisposableBean;

public class A implements DisposableBean {

	private int num;

	@Override
	public void destroy() throws Exception {
		System.out.println("destroy()");
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}

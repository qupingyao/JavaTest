package spring.ioc.ex3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("aaa")
public class A {
	
	private int id = 10;
	
	@Autowired
	private B b;
	
	public void say(){
		System.out.println(id+":"+b.getCurrentId());
	}

}

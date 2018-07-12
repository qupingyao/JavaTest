package spring.ioc.ex3;

import org.springframework.stereotype.Component;

@Component("bbb")
public class B {
	
	private int id = 15;
	
	public String getCurrentId(){
		return String.valueOf(id);
	}
	
}

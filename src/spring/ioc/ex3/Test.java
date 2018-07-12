package spring.ioc.ex3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static final String filePath = "src/spring/ioc/ex3/app-config.xml";

	public static void main(String[] args) {
		ApplicationContext context  = new FileSystemXmlApplicationContext(filePath);
		System.out.println("context build success");
		try {
			A a = (A) context.getBean("aaa");
			a.say();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
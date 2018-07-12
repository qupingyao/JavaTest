package spring.ioc.ex2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static final String appConfPath = "src/spring/ioc/ex2/app-config.xml";

	public static void main(String[] args) {
		ApplicationContext context  = new FileSystemXmlApplicationContext(appConfPath);
		System.out.println("context init success");
		try {
			A a = (A) context.getBean("a");
			System.out.println(a.getNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
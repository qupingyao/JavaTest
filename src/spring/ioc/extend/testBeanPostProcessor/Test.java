package spring.ioc.extend.testBeanPostProcessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static final String appConfPath = "src/spring/ioc/extend/testBeanPostProcessor/appConfig.xml";

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(appConfPath);
		System.out.println("context init success");
	}

}
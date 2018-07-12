package spring.ioc.extend.testBeanFactoryPostProcessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static final String appConfPath = "src/spring/ioc/extend/testBeanFactoryPostProcessor/appConfig.xml";

	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(appConfPath);
		System.out.println("context init success");
		try{
			A a = (A)ctx.getBean("a");
			System.out.println(a.getNum());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
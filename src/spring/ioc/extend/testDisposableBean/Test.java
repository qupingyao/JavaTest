package spring.ioc.extend.testDisposableBean;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static final String appConfPath = "src/spring/ioc/extend/testDisposableBean/appConfig.xml";

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext(appConfPath);
		System.out.println("context init success");
		ctx.close();
	}

}
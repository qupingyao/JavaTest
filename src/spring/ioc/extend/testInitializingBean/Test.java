package spring.ioc.extend.testInitializingBean;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static final String appConfPath = "src/spring/ioc/extend/testInitializingBean/appConfig.xml";

	public static void main(String[] args) {
		new FileSystemXmlApplicationContext(appConfPath);
		System.out.println("context init success");
	}

}
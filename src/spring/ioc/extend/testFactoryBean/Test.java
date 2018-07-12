package spring.ioc.extend.testFactoryBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Test {

	public static final String appConfPath = "src/spring/ioc/extend/testFactoryBean/appConfig.xml";

	public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(appConfPath);
		System.out.println("context init success");
		try {
			A a = (A) ctx.getBean("aFactory");
			AFactory af = (AFactory) ctx.getBean("&aFactory");
			B b = (B) ctx.getBean("b");
			BFactory bf = (BFactory) ctx.getBean("bFactory");
			System.out.println("get class-AFactory instance success? " + (af != null ? "yes" : "no")
					+ ", class-A instance num:" + a.getNum());
			System.out.println("get class-BFactory instance success? " + (bf != null ? "yes" : "no")
					+ ", class-B instance num:" + b.getNum());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
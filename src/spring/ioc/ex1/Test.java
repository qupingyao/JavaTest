package spring.ioc.ex1;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class Test {

	public static final String filePath = "src/spring/ioc/ex1/appConfig.xml";

	public static void main(String[] args) {
		Resource res = new FileSystemResource(filePath);
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(res);
		System.out.println("resource load success");
		try {
			A a = (A) factory.getBean("a");
			a.say();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
package spring.ioc.extend.testBeanFactoryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	
//	private int num;
	
	public MyBeanFactoryPostProcessor(){
		System.out.println("MyBeanFactoryPostProcessor()");
	}
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurablelistablebeanfactory)
			throws BeansException {
		System.out.println("MyBeanFactoryPostProcessor()-postProcessBeanFactory");
//		BeanDefinition define = configurablelistablebeanfactory.getBeanDefinition("a");
//		MutablePropertyValues map = define.getPropertyValues();
//		map.addPropertyValue("num",3);
	}

//	public int getNum() {
//		return num;
//	}
//
//	public void setNum(int num) {
//		this.num = num;
//	}
	
}

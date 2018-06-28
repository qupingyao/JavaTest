package spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test{
	
	public static final String filePath = "spring/app-config.xml";
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new ClassPathXmlApplicationContext(filePath);
		try{
			AInterface service = (AInterface) context.getBean("aInterface");
	        service.say();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(context!=null){
				context.close();
			}
		}
	}
	
}
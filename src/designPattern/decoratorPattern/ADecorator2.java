package designPattern.decoratorPattern;

public class ADecorator2 implements CommonInterface{
	
	public CommonInterface commonInterface;
	
	public ADecorator2(CommonInterface commonInterface){
		this.commonInterface = commonInterface;
	}

	@Override
	public void say() {
		commonInterface.say();
		System.out.println("add A2 function");
	}

}

package designPattern.decoratorPattern;

public class ADecorator extends CommonDecorator{
	
	public ADecorator(CommonInterface commonInterface){
		super(commonInterface);
	}

	@Override
	public void say() {
		commonInterface.say();
		System.out.println("add A function");
	}

}

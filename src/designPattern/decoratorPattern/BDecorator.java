package designPattern.decoratorPattern;

public class BDecorator extends CommonDecorator{
	
	public BDecorator(CommonInterface commonInterface){
		super(commonInterface);
	}

	@Override
	public void say() {
		commonInterface.say();
		System.out.println("add B function");
	}

}

package designPattern.decoratorPattern;

public class BDecorator2 extends CommonDecorator{
	
	public BDecorator2(CommonInterface commonInterface){
		super(commonInterface);
	}

	@Override
	public void say() {
		commonInterface.say();
		System.out.println("add B2 function");
	}

}

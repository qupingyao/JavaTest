package designPattern.decoratorPattern;

public class CommonDecorator implements CommonInterface{
	
	public CommonInterface commonInterface;
	
	public CommonDecorator(CommonInterface commonInterface) {
		this.commonInterface = commonInterface;
	}

	@Override
	public void say() {
		commonInterface.say();
	}

}

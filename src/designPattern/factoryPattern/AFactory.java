package designPattern.factoryPattern;

public class AFactory implements CommonFactoryInterface {

	@Override
	public CommonInterface createObj() {
		return new A();
	}

}

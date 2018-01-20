package designPattern.factoryPattern;

public class BFactory implements CommonFactoryInterface {

	@Override
	public CommonInterface createObj() {
		return new B();
	}

}

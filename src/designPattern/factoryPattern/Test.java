package designPattern.factoryPattern;

public class Test {

	public static void main(String[] args) {

		CommonFactoryInterface commonFactoryInterfaceA = new AFactory();
		CommonFactoryInterface commonFactoryInterfaceB = new BFactory();
		CommonInterface commonInterfaceA = commonFactoryInterfaceA.createObj();
		CommonInterface commonInterfaceB = commonFactoryInterfaceB.createObj();
		commonInterfaceA.say();
		commonInterfaceB.say();

	}

}

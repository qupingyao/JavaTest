package designPattern.decoratorPattern;

public class Test {

	public static void main(String[] args) {

		ADecorator aDecorator = new ADecorator(new A());
		BDecorator bDecorator = new BDecorator(new B());
		BDecorator cDecorator = new BDecorator(aDecorator);
		aDecorator.say();
		System.out.println("***********");
		bDecorator.say();
		System.out.println("***********");
		cDecorator.say();
		System.out.println("***********");
		ADecorator2 aDecorator2 = new ADecorator2(new A());
		BDecorator2 bDecorator2 = new BDecorator2(new B());
		BDecorator2 cDecorator2 = new BDecorator2(aDecorator2);
		aDecorator2.say();
		System.out.println("***********");
		bDecorator2.say();
		System.out.println("***********");
		cDecorator2.say();
		System.out.println("***********");
	}
}

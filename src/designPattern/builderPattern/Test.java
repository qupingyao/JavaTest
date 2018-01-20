package designPattern.builderPattern;

import designPattern.builderPattern.A.Builder;

public class Test {

	public static void main(String[] args) {

		A a = new Builder().setAttr1("a").setAttr2("b").setAttr3("c").build();
	}
}

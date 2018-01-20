package designPattern.builderPattern;

public class A {

	private String attr1;

	private String attr2;

	private String attr3;

	private A(Builder builder) {
		this.attr1 = builder.attr1;
		this.attr2 = builder.attr2;
		this.attr3 = builder.attr3;
	}

	public static class Builder {

		private String attr1;

		private String attr2;

		private String attr3;

		public A build() {
			return new A(this);
		}

		public Builder setAttr1(String attr1) {
			this.attr1 = attr1;
			return this;
		}

		public Builder setAttr2(String attr2) {
			this.attr2 = attr2;
			return this;
		}

		public Builder setAttr3(String attr3) {
			this.attr3 = attr3;
			return this;
		}

	}

}

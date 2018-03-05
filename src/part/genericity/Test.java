package part.genericity;

public class Test<T> {

	public T say1(T t) {
		return t;
	}

	public <E> E say2(E e) {
		return e;
	}

	/**
	 * say3()和say2()相同
	 */
	public <T> T say3(T t) {
		return t;
	}

	public static void main(String[] args) {
		Test<Father> t = new Test<Father>();
		Father f = new Father();
		Son s = new Son();
		A a = new A();
		t.say1(f);
		t.say1(s);
		// t.say1(a);
		t.say2(f);
		t.say2(s);
		t.say2(a);
		t.say3(f);
		t.say3(s);
		t.say3(a);
	}
}
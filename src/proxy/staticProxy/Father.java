package proxy.staticProxy;

/**
 * 目标类
 */
public class Father {

	public void say(String word) {
		System.out.println("I say:" + word);
	}

	public void cal(int num) {
		System.out.println("I cal:" + num);
	}

	public Father getObject() {
		System.out.println("I get Object");
		return this;
	}

}

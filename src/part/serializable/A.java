package part.serializable;

import java.io.Serializable;

public class A implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	private int age;

	public A(String name, int age) {
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format("name:%s, age:%d", name, age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
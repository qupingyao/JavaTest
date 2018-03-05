package designPattern.singletonPattern;

import java.lang.reflect.Constructor;

public class AttackSingletonByReflect {

	public static void main(String[] args) {
		try {
			Class<HungrySingleton> c = (Class<HungrySingleton>) Class
					.forName("designPattern.singletonPattern.HungrySingleton");
			Constructor<HungrySingleton> constructor = c.getDeclaredConstructor();
			constructor.setAccessible(true);
			HungrySingleton instance1 = constructor.newInstance();
			HungrySingleton instance2 = constructor.newInstance();
			System.out.println(instance1 == instance2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

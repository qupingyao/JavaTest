package load;

import java.lang.reflect.Method;

/**
 * java反射使用了硬编码，执行时涉及到大量的安全校验，这种动态调用牺牲了部分效率增加了灵活性
 */
public class TestReflectEfficiency {

	private static final int times = 10000000;

	public static void main(String[] args) {
		try {
			A a = new A();
			Method method = A.class.getDeclaredMethod("count");
			method.setAccessible(true);
			long t1 = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				a.count();
			}
			long t2 = System.currentTimeMillis();
			for (int i = 0; i < times; i++) {
				method.invoke(a);
			}
			long t3 = System.currentTimeMillis();
			System.out.println("instance call cost time:" + (t2 - t1));
			System.out.println("reflect call cost time:" + (t3 - t2));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static class A {

		private void count() {
			int sum = 0;
			for (int i = 0; i < 1000; i++) {
				sum = sum + i;
			}
		}
	}

}

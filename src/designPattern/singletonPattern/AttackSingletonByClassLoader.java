package designPattern.singletonPattern;

import java.lang.reflect.Method;

import designPattern.singletonPattern.MyClassLoader;

public class AttackSingletonByClassLoader {

	public static void main(String[] args) {
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
		ClassLoader extClassLoader = sysClassLoader.getParent();
		MyClassLoader myClassLoader = new MyClassLoader(extClassLoader);
		try {
			Class<?> c1 = myClassLoader.loadClass("designPattern.singletonPattern.HungrySingleton");/** 自定义加载器加载 **/
			Class<?> c2 = sysClassLoader.loadClass("designPattern.singletonPattern.HungrySingleton");/** 系统类加载器加载 **/
			Method method1 = c1.getDeclaredMethod("getInstance");
			Object obj1 = method1.invoke(null);
			Method method2 = c2.getDeclaredMethod("getInstance");
			Object obj2 = method2.invoke(null);
			System.out.println(obj1 == obj2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package Load;

import java.lang.reflect.Method;

public class TestMyClassLoader{
	
	public static void main(String[] args) {
	
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
		ClassLoader extClassLoader = sysClassLoader.getParent();
		MyClassLoader myClassLoader = new MyClassLoader(extClassLoader);  
		try {
			Class<?> c1 = myClassLoader.loadClass("Load.Person");/**自定义加载器加载**/
			Class<?> c2 = sysClassLoader.loadClass("Load.Person");/**系统类加载器加载**/
			Object obj1 = c1.newInstance();
			Object obj2 = c2.newInstance();
			Method method1 = c1.getDeclaredMethod("say");
			method1.invoke(obj1);
			Method method2 = c2.getDeclaredMethod("say");
			method2.invoke(obj2);
			System.out.println(obj1.getClass());
		    System.out.println(obj1 instanceof Load.Person);
		    System.out.println(obj2.getClass());
		    System.out.println(obj2 instanceof Load.Person);
		    System.out.println("java ext dir:"+System.getProperty("java.ext.dirs"));
	        System.out.println("java class path:"+System.getProperty("java.class.path"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
    
}

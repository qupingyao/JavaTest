package Load;

public class TestMyClassLoader{
	
	public static void main(String[] args) {
	
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();
		ClassLoader extClassLoader = sysClassLoader.getParent();
		MyClassLoader myClassLoader = new MyClassLoader(extClassLoader);  
		try {
			Class<?> c1 = myClassLoader.loadClass("Load.Person");/**自定义加载器加载**/
			Class<?> c2 = sysClassLoader.loadClass("Load.Person");/**系统类加载器加载**/
			Object obj1 = c1.newInstance();
			System.out.println(obj1.getClass());
		    System.out.println(obj1 instanceof Load.Person);
		    Object obj2 = c2.newInstance();
		    System.out.println(obj2.getClass());
		    System.out.println(obj2 instanceof Load.Person);
		    System.out.println("java ext dir:"+System.getProperty("java.ext.dirs"));
	        System.out.println("java class path:"+System.getProperty("java.class.path"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
    
}

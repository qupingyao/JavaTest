package Load;
import java.lang.*;

import javax.crypto.Cipher;

public class Test{
	
	public static void main(String[] args) {
//		try {
//			MyClassLoader myClassLoader = new MyClassLoader();
//			Object obj1 = myClassLoader.loadClass("Load.Father").newInstance();
//			System.out.println(obj1.getClass());
//			System.out.println(obj1 instanceof Load.ClassLoaderTest2);
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try{
			
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
			classLoader.loadClass("Load.Person");
			Class<?> c1 = Class.forName("Load.Person", false, classLoader); 
			System.out.println(c1.getName());
			System.out.println(c1.getClassLoader());
		}catch(Exception e){
			e.printStackTrace();
		}
//		MyClassLoader mcl = new MyClassLoader();        
//		Object obj;
//		try {
//			Class<?> c1 = Class.forName("Load.Person", true, mcl); 
//			obj = c1.newInstance();
//			System.out.println(obj);
//			System.out.println(obj.getClass().getClassLoader());
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	        System.out.println(ClassLoader.getSystemClassLoader());
//	        System.out.println(ClassLoader.getSystemClassLoader().getParent());
//	        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
//	        System.out.println(System.getProperty("java.ext.dirs"));
//	        System.out.println(System.getProperty("java.class.path"));
	}
    
}

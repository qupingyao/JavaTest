package Load;
/**
 * 不触发Person类的初始化,没有创建Person对象,只是个数组对象
 */
public class Exm2 {
	
	public static void main(String[] args) {
		Person[] arr = new Person[10];
	}
    
}

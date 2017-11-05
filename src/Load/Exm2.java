package Load;
/**
 * 不触发Super类的初始化，没有创建Super对象,只是个数组对象
 */
public class Exm2 {
	
	public static void main(String[] args) {
		Super[] arr = new Super[10];
	}
    
}

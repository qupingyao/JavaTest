package load;

/**
 * 不触发A类的初始化,没有创建A类实例对象,只是个数组对象
 */
public class Exm2 {

	public static void main(String[] args) {
		A[] arr = new A[10];
	}

}

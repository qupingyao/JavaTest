package Load;
/**
 * 一道阿里笔试题
 */
public class ComplexLoad {
	
    public static int k = 0;
    
    public static ComplexLoad ob1 = new ComplexLoad("ob1");
    
    public static ComplexLoad ob2 = new ComplexLoad("ob2");
    
    public static int i = print("i");
    
    public static int n = 99;
    
    public int j = print("j");
    
    {
        print("construct block");
    }
    
    static {
        print("static block");
    }
    
    public ComplexLoad(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++i;
        ++n;
    }
    
    public static int print(String str) {
        System.out.println((++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++n;
        return ++i;
    }
    
    public static void main(String args[]) {
    	ComplexLoad complexLoad = new ComplexLoad("init");
    }
}

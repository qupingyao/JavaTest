package Load;

public class Person {
	
	static{
		System.out.println("person class static block");
	}
	
	private String name;
	
	public Person(){
    	
    }
    
    public Person(String name){
        this.name = name;
    }
    
    public void say(){
    	System.out.println("I'm a boy");
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
}

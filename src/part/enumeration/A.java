package part.enumeration;

public class A {

	public String color;
	
	public A(Color color){
		this.color = color.getName();
	}

	public String getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color.getName();
	}
	
}

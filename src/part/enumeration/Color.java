package part.enumeration;

public enum Color {

	RED("红色"), BLUE("蓝色"), GREEN("绿色");

	private String name;

	private Color(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
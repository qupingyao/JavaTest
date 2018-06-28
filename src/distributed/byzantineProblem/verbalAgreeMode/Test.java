package distributed.byzantineProblem.verbalAgreeMode;

public class Test {
	
	private static final int personNum = 6;
	
	private static final int traitorNum = 2;
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			DoAlgorithm.exec(personNum, traitorNum);
		}
	}
	
}

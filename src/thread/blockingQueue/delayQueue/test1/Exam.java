package thread.blockingQueue.delayQueue.test1;

import java.util.Random;
import java.util.concurrent.DelayQueue;

public class Exam {
	
	public static final int studentNum = 20;
	
	public static final int minSubmitTime = 5;
	
	public static final int forceSubmitTime = 60;
	
	public static void main(String[] args) {
		DelayQueue<Student> delayQueue = new DelayQueue<Student>();
		Random random = new Random();
		for (int i = 0; i < studentNum; i++) {
			delayQueue.put(new Student("student-" + (i + 1), random.nextInt(100)));
		}
		new Thread(new Teacher(delayQueue)).start();
	}
	
}

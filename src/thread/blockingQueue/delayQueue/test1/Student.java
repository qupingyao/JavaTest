package thread.blockingQueue.delayQueue.test1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable, Delayed {

	private String name;
	private long workTime;
	private long submitTime;

	public Student(String name, long workTime) {
		this.name = name;
		this.workTime = workTime;
		long t = workTime;
		if(workTime<Exam.minSubmitTime){
			t = Exam.minSubmitTime;
		}
		if(workTime>Exam.forceSubmitTime){
			t = Exam.forceSubmitTime;
		}
		this.submitTime = TimeUnit.SECONDS.convert(t, TimeUnit.SECONDS) + System.currentTimeMillis()/1000;
	}

	@Override
	public int compareTo(Delayed o) {
		if (o == null || !(o instanceof Student)){
			return 0;
		}
		if (o == this){
			return 0;
		}
		Student s = (Student) o;
		if (this.submitTime > s.submitTime) {
			return 1;
		}
		if (this.submitTime == s.submitTime) {
			return 0;
		} 
		if (this.submitTime < s.submitTime) {
			return -1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(submitTime - System.currentTimeMillis()/1000, TimeUnit.SECONDS);
	}

	@Override
	public void run() {
		System.out.println(name + " submit, workTime:" + workTime + ", submitTime:" + new SimpleDateFormat("YY-MM-dd HH:mm:ss").format(new Date(submitTime*1000)));
//		System.out.println(name + " submit, workTime:" + workTime + ", submitTime:" + new SimpleDateFormat("YY-MM-dd HH:mm:ss").format(new Date(submitTime)));
	}

}

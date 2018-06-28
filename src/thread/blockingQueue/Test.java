package thread.blockingQueue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test{

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		long time = TimeUnit.SECONDS.toNanos(5);
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//	            while(time)
				try{
	            	condition.awaitNanos(time);
	            }catch (Exception e) {
					e.printStackTrace();
				}
	            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				lock.unlock();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				try{
					Thread.sleep(10000);
				}catch (Exception e) {
					e.printStackTrace();
				}
				t1.interrupt();
				lock.unlock();
			}
		});
		t1.start();
		t2.start();
	}

}

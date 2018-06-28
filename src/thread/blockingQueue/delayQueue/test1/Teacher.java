package thread.blockingQueue.delayQueue.test1;

import java.util.concurrent.DelayQueue;

class Teacher implements Runnable {

	private DelayQueue<Student> delayQueue;

	public Teacher(DelayQueue<Student> delayQueue) {
		this.delayQueue = delayQueue;
	}

	@Override
	public void run() {
		try {
			System.out.println("test start");
			while (delayQueue.size()>0) {
				delayQueue.take().run();
			}
			System.out.println("test end");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

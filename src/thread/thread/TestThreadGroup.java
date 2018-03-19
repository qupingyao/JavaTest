package thread.thread;

public class TestThreadGroup {

	public static void main(String[] args) {

		ThreadGroup mainTGroup = Thread.currentThread().getThreadGroup();
		ThreadGroup systemTGroup = mainTGroup.getParent();
		ThreadGroup fatherTGroup = new ThreadGroup(mainTGroup, "fatherTGroup");
		ThreadGroup sonTGroup = new ThreadGroup(fatherTGroup, "sonTGroup");
		Thread mainThread = Thread.currentThread();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					mainThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t1");
		Thread t2 = new Thread(fatherTGroup, new Runnable() {
			@Override
			public void run() {
				try {
					mainThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t2");
		Thread t3 = new Thread(sonTGroup, new Runnable() {
			@Override
			public void run() {
				try {
					mainThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "t3");
		t1.start();
		t2.start();
		t3.start();
		systemTGroup.list();

	}

}

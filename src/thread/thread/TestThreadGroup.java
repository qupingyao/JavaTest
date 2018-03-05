package thread.thread;

public class TestThreadGroup {

	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadGroup("group1"), "thread1") {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};

		Thread t2 = new Thread("thread2") {
			@Override
			public void run() {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t1.start();
		t2.start();
		ThreadGroup group = Thread.currentThread().getThreadGroup().getParent();
		group.list();

	}

}

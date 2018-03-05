package thread.thread;

public class TestInterruptWhenWait {

	public static void main(String[] args) {
		Object lock = new Object();
		Thread ta = new Thread(new TestInterruptWhenWait.A(lock));
		Thread tb = new Thread(new TestInterruptWhenWait.B(lock, ta));
		ta.start();
		tb.start();
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		ta.interrupt();
		System.out.println("main thread end");
	}

	private static class A implements Runnable {

		public Object lock;

		public A(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {

			synchronized (lock) {
				System.out.println("thread a start");
				try {
					lock.wait();
					// System.out.println("thread a end");
				} catch (InterruptedException e) {
					System.out.println("catch interruptedException");
				}
			}

		}
	}

	public static class B implements Runnable {

		private Object lock;

		private Thread thread;

		public B(Object lock, Thread thread) {
			this.lock = lock;
			this.thread = thread;
		}

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("thread b start");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread b isInterrupted flag:" + thread.isInterrupted());
				System.out.println("thread b end");
			}
		}

	}

}
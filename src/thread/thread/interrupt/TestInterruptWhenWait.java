package thread.thread.interrupt;

public class TestInterruptWhenWait {

	public static void main(String[] args) {

		Object lock = new Object();
		ThreadA ta = new ThreadA(lock);
		ThreadB tb = new ThreadB(lock);
		ta.setLaunchThread(tb);
		tb.setObserveThread(ta);
		ta.start();
		System.out.println("main thread run to end");
	}

	private static class ThreadA extends Thread {

		private Object lock;

		private Thread launchThread;

		public ThreadA(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("thread-a run start");
					launchThread.start();
					lock.wait();
				}
			} catch (InterruptedException e) {
				System.out.println("thread-a catch InterruptedException");
			}

		}

		private void setLaunchThread(Thread launchThread) {
			this.launchThread = launchThread;
		}
	}

	private static class ThreadB extends Thread {

		private Object lock;

		private Thread observeThread;

		public ThreadB(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("thread-a isInterrupted:" + observeThread.isInterrupted());
				observeThread.interrupt();
				System.out.println("thread-a isInterrupted:" + observeThread.isInterrupted());
				try {
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("thread-b run to end");
			}
		}

		private void setObserveThread(Thread observeThread) {
			this.observeThread = observeThread;
		}

	}

}
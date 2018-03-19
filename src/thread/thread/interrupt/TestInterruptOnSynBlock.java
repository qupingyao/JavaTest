package thread.thread.interrupt;

public class TestInterruptOnSynBlock {

	public static void main(String[] args) {

		Object lock = new Object();
		Thread mainThread = Thread.currentThread();
		ThreadA ta = new ThreadA(lock);
		ThreadB tb = new ThreadB(lock);
		ta.setLaunchThread(tb);
		ta.setWaitThread(mainThread);
		ta.start();
		while (!Thread.State.BLOCKED.equals(tb.getState())) {
		}
		tb.interrupt();
		System.out.println("main thread find thread-b isInterrupted:" + tb.isInterrupted());
		System.out.println("main thread run to end");
	}

	private static class ThreadA extends Thread {

		private Object lock;

		private Thread launchThread;

		private Thread waitThread;

		public ThreadA(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("thread-a run start");
				launchThread.start();
				try {
					waitThread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("thread-a run to end");
			}
		}

		private void setLaunchThread(Thread launchThread) {
			this.launchThread = launchThread;
		}

		private void setWaitThread(Thread waitThread) {
			this.waitThread = waitThread;
		}
	}

	private static class ThreadB extends Thread {

		private Object lock;

		public ThreadB(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("thread-b find isInterrupted:" + Thread.currentThread().isInterrupted());
			}
		}

	}

}

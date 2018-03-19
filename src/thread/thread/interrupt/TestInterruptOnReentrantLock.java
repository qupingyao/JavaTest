package thread.thread.interrupt;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 该方法可能需要运行多次才能执行到最后输出jvm exit
 */
public class TestInterruptOnReentrantLock {

	public static void main(String[] args) {

		ReentrantLock lock = new ReentrantLock();
		Thread mainThread = Thread.currentThread();
		ThreadA ta = new ThreadA(lock);
		ThreadB tb = new ThreadB(lock);
		ta.setLaunchThread(tb);
		ta.setWaitThread(mainThread);
		ta.start();

		while (!Thread.State.WAITING.equals(tb.getState())) {
		}
		tb.interrupt();
		while (!Thread.State.RUNNABLE.equals(tb.getState())) {
		}
		while (!Thread.State.WAITING.equals(tb.getState())) {
		}
		System.out.println("main thread find thread-b isInterrupted:" + tb.isInterrupted());
		System.out.println("main thread run to end");
	}

	private static class ThreadA extends Thread {

		private ReentrantLock lock;

		private Thread launchThread;

		private Thread waitThread;

		public ThreadA(ReentrantLock lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			lock.lock();
			System.out.println("thread-a run start");
			launchThread.start();
			try {
				waitThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("thread-a run to end");
			lock.unlock();
		}

		private void setLaunchThread(Thread launchThread) {
			this.launchThread = launchThread;
		}

		private void setWaitThread(Thread waitThread) {
			this.waitThread = waitThread;
		}
	}

	private static class ThreadB extends Thread {

		private ReentrantLock lock;

		public ThreadB(ReentrantLock lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			lock.lock();
			System.out.println("thread-b find isInterrupted:" + Thread.currentThread().isInterrupted());
			System.out.println("jvm exit");
			lock.unlock();
		}

	}

}

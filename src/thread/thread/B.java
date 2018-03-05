package thread.thread;

public class B implements Runnable {

	private Object lock;
	

	public B(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		synchronized (lock) {
			System.out.println("thread b start");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("thread b end");
		}
	}

}

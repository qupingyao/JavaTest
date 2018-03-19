package thread.thread.interrupt;

public class TestPreInterrupt {

	private static volatile boolean isAfterInterrupt = false;

	public static void main(String[] args) {

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!isAfterInterrupt) {
				}
				System.out.println("thread run after cycle,isInterrupted:" + Thread.currentThread().isInterrupted());
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.out.println("thread catch InterruptedException,isInterrupted:"
							+ Thread.currentThread().isInterrupted());
				}
				System.out.println("thread run to end");
			}
		});
		t.start();
		t.interrupt();
		isAfterInterrupt = true;
		System.out.println("main thread run to end");
	}

}

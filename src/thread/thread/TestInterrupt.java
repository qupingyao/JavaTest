package thread.thread;

public class TestInterrupt {

	public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				long now = System.currentTimeMillis();
				while (System.currentTimeMillis() - now < 5000) {
				}
				System.out.println("thread after cycle");
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					System.out.println("thread catch InterruptedException,isInterrupted flag:"
							+ Thread.currentThread().isInterrupted());
				}
				System.out.println("thread run to end");
			}
		});
		t.start();
		try {
			Thread.sleep(1);
			t.interrupt();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("main thread run to end");
	}

}

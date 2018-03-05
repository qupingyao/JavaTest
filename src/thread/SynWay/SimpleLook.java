package thread.SynWay;

import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleLook {
	
	private static int num;
	
	private AtomicBoolean state = new AtomicBoolean(false);

	public void lock() {
		while (state.getAndSet(true)) {
		}
	}

	public void unlock() {
		state.set(false);
	}
	
	public static void main(String[] args) {
		SimpleLook lock = new SimpleLook();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<10000;i++){
					lock.lock();
					num++;
					lock.unlock();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<10000;i++){
					lock.lock();
					num++;
					lock.unlock();
				}
			}
		});
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(num);
	}
}

package thread.thread;

public class A implements Runnable {
	
	public Object lock;
	
	public A(Object lock){
		this.lock = lock;
	}
	
	@Override
	public void run() {
		System.out.println("Thread a wait");
			synchronized (lock) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					System.out.println("a catch");
					e.printStackTrace();
				}
			}
	}

}

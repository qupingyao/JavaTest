package thread.thread;

public class Test {

	public static void main(String[] args) {
		Object lock = new Object();
		Thread t1 = new Thread(new A(lock));
		Thread t2 = new Thread(new B(lock));
		t2.start();
		t1.start();
		t1.interrupt();
		System.out.println("main thread end");
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

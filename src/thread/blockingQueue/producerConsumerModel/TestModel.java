package thread.blockingQueue.producerConsumerModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class TestModel {

	private final static int initCapacity = 3;

	public static void main(String[] args) {

		Depot depot1 = new Depot(new LinkedBlockingQueue<Thing>(initCapacity));
		Depot depot2 = new Depot(new MyLinkedBlockingingQueue<Thing>(initCapacity));
		List<Thread> tList = new ArrayList<Thread>();
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Productor(depot1, "productor-" + i));
			tList.add(t);
			t.start();
		}
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Consumer(depot1, "consumer-" + i));
			tList.add(t);
			t.start();
		}
		for (Thread t : tList) {
			try {
				t.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		tList.clear();
		System.out.println("depot use linkedBlockingQueue end");
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Productor(depot2, "productor-" + i));
			tList.add(t);
			t.start();
		}
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new Consumer(depot2, "consumer-" + i));
			tList.add(t);
			t.start();
		}
		for (Thread t : tList) {
			try {
				t.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("depot use myLinkedBlockingQueue end");

	}

}
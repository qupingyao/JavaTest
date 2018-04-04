package thread.thread.producerConsumerModel;

import java.util.LinkedList;

public class Depot {

	private int maxNum;

	private LinkedList<Thing> list;

	public Depot(int maxNum) {
		this.maxNum = maxNum;
		list = new LinkedList<Thing>();
	}

	public synchronized void product(String productorName, Thing thing) {
		while (list.size() >= maxNum) {
			System.out.println("depot is full");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.add(thing);
		System.out.println(productorName + " product, thingId: " + thing.getId() + ", remain:" + list.size());
		this.notifyAll();
	}

	public synchronized void consume(String consumerName) {
		while (list.size() <= 0) {
			System.out.println("depot is empty");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Thing thing = list.pop();
		System.out.println(consumerName + " consume, thingId: " + thing.getId() + ", remain: " + list.size());
		this.notifyAll();
	}

}

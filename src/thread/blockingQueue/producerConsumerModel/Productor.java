package thread.blockingQueue.producerConsumerModel;

import thread.blockingQueue.producerConsumerModel.Depot;
import thread.blockingQueue.producerConsumerModel.Thing;

public class Productor implements Runnable {

	private Depot depot;

	private String name;

	public Productor(Depot depot, String name) {
		this.depot = depot;
		this.name = name;
	}

	@Override
	public void run() {
		depot.product(name, new Thing());
	}
}

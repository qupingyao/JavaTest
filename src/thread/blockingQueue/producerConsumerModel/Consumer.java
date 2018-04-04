package thread.blockingQueue.producerConsumerModel;

import thread.blockingQueue.producerConsumerModel.Depot;

public class Consumer implements Runnable {

	private Depot depot;

	private String name;

	public Consumer(Depot depot, String name) {
		this.depot = depot;
		this.name = name;
	}

	@Override
	public void run() {
		depot.consume(name);
	}
}
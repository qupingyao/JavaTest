package thread.blockingQueue.producerConsumerModel;

import java.util.concurrent.BlockingQueue;

import thread.blockingQueue.producerConsumerModel.Thing;

public class Depot {

	private BlockingQueue<Thing> blockingQueue;

	public Depot(BlockingQueue<Thing> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	public void product(String productorName, Thing thing) {
		try {
			blockingQueue.put(thing);
			System.out.println(productorName + " product complete and exit, thingId: " + thing.getId());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void consume(String consumerName) {
		try {
			Thing thing = blockingQueue.take();
			System.out.println(consumerName + " consume complete and exit, thingId: " + thing.getId());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
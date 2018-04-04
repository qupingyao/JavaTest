package thread.blockingQueue.producerConsumerModel;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import thread.blockingQueue.producerConsumerModel.Thing;

public class Depot {
	
	private BlockingQueue<Thing> blockingQueue;

	public Depot(int maxNum) {
		this.blockingQueue = new LinkedBlockingQueue<Thing>(maxNum);
	}

	public void product(String productorName,Thing thing) {
		try{
			blockingQueue.put(thing);
			System.out.println(productorName + " product, thingId: " + thing.getId() + ", remain:" + blockingQueue.size());
		}catch(InterruptedException e ){
			e.printStackTrace();
		}
	}

	public void consume(String consumerName) {
		try {
			Thing thing = blockingQueue.take();
			System.out.println(consumerName + " consume, thingId: " + thing.getId() + ", remain: " + blockingQueue.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
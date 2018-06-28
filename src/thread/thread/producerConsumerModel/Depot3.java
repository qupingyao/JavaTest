package thread.thread.producerConsumerModel;

import java.util.concurrent.LinkedBlockingQueue;

/**官方队列+官方代码版本**/
public class Depot3 extends Depot{

	public Depot3(int maxNum) {
		super.blockingQueue = new LinkedBlockingQueue<Thing>(maxNum);
	}
	
	@Override
	public void product(String productorName, Thing thing) {
		try{
			blockingQueue.put(thing);
			System.out.println(productorName + " product, thingId: " + thing.getId() + ", remain:" + blockingQueue.size());
		}catch(InterruptedException e ){
			e.printStackTrace();
		}
	}
	
	@Override
	public void consume(String consumerName) {
		try {
			Thing thing = blockingQueue.take();
			System.out.println(consumerName + " consume, thingId: " + thing.getId() + ", remain: " + blockingQueue.size());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void add(Thing thing){
	
	}

	@Override
	public int getProductorNotEmptySignalCount() {
		return 0;
	}

	@Override
	public int getConsumerNotEmptySignalCount() {
		return 0;
	}

	@Override
	public int getProductorNotFullSignalCount() {
		return 0;
	}

	@Override
	public int getConsumerNotFullSignalCount() {
		return 0;
	}
	
	@Override
	public void clear(){
		blockingQueue.clear();
	}
	
	@Override
	public int getCount(){
		return blockingQueue.size();
	}
	
}

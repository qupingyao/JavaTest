package thread.thread.producerConsumerModel;

/**自定义队列+官方代码版本**/
public class Depot2 extends Depot{

	public Depot2(int maxNum) {
		super.capacity = capacity;
	}
	
	@Override
	public void product(String productorName, Thing thing) {
		int c = -1;
		putLock.lock();
		while (count.get() == capacity) {
			System.out.println(productorName + " find depot is full");
			try {
				notFull.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		list.add(thing);
		c = count.getAndIncrement();
		System.out.println(productorName + " product, thingId: " + thing.getId() + ", remain:" + list.size());
		if (c + 1 < capacity){
			notFull.signal();
		}
		putLock.unlock();
		if (c==0) {
			takeLock.lock();
			notEmpty.signal();
			takeLock.unlock();
		}
	
	}
	
	@Override
	public void consume(String consumerName) {
		int c = -1;
		takeLock.lock();
		while (count.get() == 0) {
			System.out.println(consumerName + " find depot is empty");
			try {
				notEmpty.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Thing thing = list.pop();
		c = count.getAndDecrement();
		System.out.println(consumerName + " consume, thingId: " + thing.getId() + ", remain: " + list.size());
		if (c > 1){
			notEmpty.signal();
		}
		takeLock.unlock();
		if (c == capacity) {
			putLock.lock();
			notFull.signal();
			putLock.unlock();
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
		
	}
	
	@Override
	public int getCount(){
		return count.get();
	}
	
}

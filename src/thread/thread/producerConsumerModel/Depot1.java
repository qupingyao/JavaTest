package thread.thread.producerConsumerModel;

/**自定义队列+自定义无脑唤醒版本**/
public class Depot1 extends Depot{

	public Depot1(int capacity) {
		super.capacity = capacity;
	}
	
	@Override
	public void product(String productorName, Thing thing) {
		System.out.println(productorName + " request productLock");
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
		count.getAndIncrement();
		System.out.println(productorName + " product, thingId: " + thing.getId() + ", remain:" + list.size());
		putLock.unlock();
		System.out.println(productorName + " release productLock");
		System.out.println(productorName + " request consumeLock");
		takeLock.lock();
		notEmpty.signal();
		takeLock.unlock();
		System.out.println(productorName + " release consumeLock");
	
	}
	
	@Override
	public void consume(String consumerName) {
		System.out.println(consumerName + " request consumeLock");
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
		count.getAndDecrement();
		System.out.println(consumerName + " consume, thingId: " + thing.getId() + ", remain: " + list.size());
		takeLock.unlock();
		System.out.println(consumerName + " release consumeLock");
		System.out.println(consumerName + " request productLock");
		putLock.lock();
		notFull.signal();
		putLock.unlock();
		System.out.println(consumerName + " release productLock");
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

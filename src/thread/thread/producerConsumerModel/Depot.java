package thread.thread.producerConsumerModel;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Depot {

	protected int capacity;

	protected AtomicInteger count = new AtomicInteger(0);
	
	protected LinkedList<Thing> list = new LinkedList<Thing>();

	protected ReentrantLock putLock = new ReentrantLock();

	protected ReentrantLock takeLock = new ReentrantLock();

	protected Condition notFull = putLock.newCondition();

	protected Condition notEmpty = takeLock.newCondition();
	
	protected BlockingQueue<Thing> blockingQueue;
	
	protected MyBlockingQueue<Thing> myBlockingQueue;

	public abstract void product(String productorName, Thing thing);
	
	public abstract void consume(String consumerName);
	
	public abstract void add(Thing thing);
	
	public abstract int getProductorNotEmptySignalCount();
	
	public abstract int getConsumerNotEmptySignalCount();
	
	public abstract int getProductorNotFullSignalCount();
	
	public abstract int getConsumerNotFullSignalCount();
	
	public abstract void clear();
	
	public abstract int getCount();
	

}

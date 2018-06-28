package thread.thread.producerConsumerModel;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyLinkedBlockingQueue1<E> implements MyBlockingQueue<E>{
	
    private final int capacity;

    private final AtomicInteger count = new AtomicInteger();

    transient Node<E> head;

    private transient Node<E> last;

    private final ReentrantLock takeLock = new ReentrantLock();

    private final Condition notEmpty = takeLock.newCondition();

    private final ReentrantLock putLock = new ReentrantLock();

    private final Condition notFull = putLock.newCondition();
    
    private final AtomicInteger productorNotEmptySignalCount = new AtomicInteger(0);
    
    private final AtomicInteger consumerNotEmptySignalCount = new AtomicInteger(0);
	
    private final AtomicInteger productorNotFullSignalCount = new AtomicInteger(0);
    
    private final AtomicInteger consumerNotFullSignalCount = new AtomicInteger(0);
    
    public MyLinkedBlockingQueue1(int capacity) {
        this.capacity = capacity;
        last = head = new Node<E>(null);
    }
    
    @Override
    public void put(E e) {
        int c = -1;
        Node<E> node = new Node<E>(e);
        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;
        putLock.lock();
        while (count.get() == capacity) {
        	try{
        		notFull.await();
        	}catch (Exception ex) {
				ex.printStackTrace();
			}
        }
        enqueue(node);
        c = count.getAndIncrement();
        if (c + 1 < capacity){
        	productorNotFullSignalCount.getAndIncrement();
            notFull.signal();
        }
        putLock.unlock();
        if (c == 0){
            signalNotEmpty();
        }
    }
    
    @Override
    public E take() {
        E x;
        int c = -1;
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        while (count.get() == 0) {
        	try{
        		notEmpty.await();
	        }catch (Exception ex) {
				ex.printStackTrace();
			}
        }
        x = dequeue();
        c = count.getAndDecrement();
        if (c > 1){
        	consumerNotEmptySignalCount.getAndIncrement();
            notEmpty.signal();
        }
        takeLock.unlock();
        if (c == capacity){
            signalNotFull();
        }
        return x;
    }
    
    @Override
    public int size() {
        return count.get();
    }
    
    @Override
    public void add(E e){
    	
    }
    
    @Override
    public int getProductorNotEmptySignalCount(){
    	return productorNotEmptySignalCount.get();
    }
	
    @Override
	public int getConsumerNotEmptySignalCount(){
    	return consumerNotEmptySignalCount.get();
    }
	
    @Override
	public int getProductorNotFullSignalCount(){
    	return productorNotFullSignalCount.get();
    }
	
    @Override
	public int getConsumerNotFullSignalCount(){
    	return consumerNotFullSignalCount.get();
    }
    
    @Override
    public void clear() {
        fullyLock();
        for (Node<E> p, h = head; (p = h.next) != null; h = p) {
            h.next = h;
            p.item = null;
        }
        head = last;
        if (count.getAndSet(0) == capacity){
        	consumerNotFullSignalCount.getAndIncrement();
            notFull.signal();
        }
        fullyUnlock();
    }
    
    private void enqueue(Node<E> node) {
        last = last.next = node;
    }
    
    private E dequeue() {
        Node<E> h = head;
        Node<E> first = h.next;
        h.next = h; 
        head = first;
        E x = first.item;
        first.item = null;
        return x;
    }

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
        	productorNotEmptySignalCount.getAndIncrement();
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }
    
    private void signalNotFull() {
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
        	consumerNotFullSignalCount.getAndIncrement();
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }
    
    private void fullyLock() {
        putLock.lock();
        takeLock.lock();
    }

    private void fullyUnlock() {
        takeLock.unlock();
        putLock.unlock();
    }
    
    private static class Node<E> {
    	
        private E item;

        private Node<E> next;

        private Node(E x) { item = x; }
    }
}

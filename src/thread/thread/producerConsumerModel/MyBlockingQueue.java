package thread.thread.producerConsumerModel;

public interface MyBlockingQueue<E> {
	
	public void put(E e);
    
    public E take();
    
    public int size();
    
    public void add(E e);
    
    public int getProductorNotEmptySignalCount();
	
	public int getConsumerNotEmptySignalCount();
	
	public int getProductorNotFullSignalCount();
	
	public int getConsumerNotFullSignalCount();
	
	public void clear();
    
}

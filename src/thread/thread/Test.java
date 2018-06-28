package thread.thread;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import org.omg.CORBA.PUBLIC_MEMBER;

import thread.blockingQueue.producerConsumerModel.Thing;

public class Test {

	public static void main(String[] args)throws Exception {
		LinkedBlockingQueue<Thing> blockingQueue = new LinkedBlockingQueue<Thing>(100);
		blockingQueue.clear();
		ArrayBlockingQueue<Thing> arrayBlockingQueue = new ArrayBlockingQueue<Thing>(100,true);
		arrayBlockingQueue.remove();
		arrayBlockingQueue.element();
		arrayBlockingQueue.peek();
		blockingQueue.addAll(new ArrayList<Thing>());
		new PriorityBlockingQueue<Thing>(100);
//		new SynchronousQueue(100);
//		ReentrantLock lock = new ReentrantLock();
//		lock.lockInterruptibly();
//		Thread t1 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				lock.lock();
//				System.out.println("t1 start");
//				for(int i=0;i<10;i++){
//					try{
//						Thread.sleep(3000);
//					}catch(Exception e){
//						e.printStackTrace();
//					}
//				}
//				System.out.println("t1 end");
//				lock.unlock();
//			}
//		});
//		t1.start();
//		try{
//			Thread.sleep(3000);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		Thread t2 = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				System.out.println("t2 start");
//				try{
//					boolean get = lock.tryLock(3,TimeUnit.SECONDS);
//					System.out.println(get);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				System.out.println("t2 end");
//			}
//		});
//		t2.start();
	
				
	}

}

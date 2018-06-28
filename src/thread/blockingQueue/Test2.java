package thread.blockingQueue;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import thread.blockingQueue.delayQueue.test1.Student;

public class Test2 {

    public static final int CAPICITY = 10000;  
    
    public static final int THREAD_NUM = 1000000;  
      
    public static void test(final BlockingQueue<Product> queue,String name) {  
        
        class Producer implements Runnable{  
            @Override  
            public void run() {  
                try {  
                    queue.put(new Product("a"));  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
              
        };  
        class Consumer implements Runnable{  
            @Override  
            public void run(){  
                try {  
                    queue.take();  
                } catch (InterruptedException e) {  
                    e.printStackTrace();  
                }  
            }  
        };  
        Thread[] arrProducerThread = new Thread[THREAD_NUM]; 
        Thread[] arrConsumerThread = new Thread[THREAD_NUM]; 
        for(int i=0;i<THREAD_NUM;i++){  
            arrProducerThread[i] = new Thread(new Producer());  
        }  
        for(int i=0;i<THREAD_NUM;i++){  
            arrConsumerThread[i] = new Thread(new Consumer());  
        }  
        long t1 = System.currentTimeMillis();  
        for(int i=0;i<THREAD_NUM;i++){  
            arrProducerThread[i].start();  
            arrConsumerThread[i].start();  
        }  
        for(int i=0;i<THREAD_NUM;i++){  
        	try{
	            arrProducerThread[i].join();  
	            arrConsumerThread[i].join();  
        	}catch (Exception e) {
				e.printStackTrace();
			}
        }  
        long t2 = System.currentTimeMillis();  
        System.out.println(name+" cost : " + (t2-t1));  
    }  
    public static void main(String[] args) throws InterruptedException{  
        final BlockingQueue<Product> q1 = new LinkedBlockingQueue<Product>(CAPICITY);  
        final BlockingQueue<Product> q2 = new ArrayBlockingQueue<Product>(CAPICITY);
        final BlockingQueue<Product> q3 = new PriorityBlockingQueue<Product>(CAPICITY);
        final BlockingQueue<Product> q4 = new DelayQueue<Product>();
        final BlockingQueue<Product> q5 = new LinkedBlockingDeque<Product>(CAPICITY);
        final BlockingQueue<Product> q6 = new SynchronousQueue<Product>();
        q6.put(new Product("100"));
        Map<String, String> map = new Hashtable<String,String>(50);
//        DelayQueue  
//    	Product p1 = new Product("1");
//        Product p2 = new Product("2");
//        Product p3 = new Product("3");
//        Product p4 = new Product("4");
//        Product p5 = new Product("5");
//        q3.put(p1);
//        q3.put(p2);
//        q3.put(p3);
//        q3.put(p4);
//        q3.put(p5);
        int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
        System.out.println(MAX_ARRAY_SIZE);
        int oldCap = Integer.MAX_VALUE-9;
        System.out.println(oldCap);
        int newCap = oldCap + ((oldCap < 64) ?(oldCap + 2) : // grow faster if small
                (oldCap >> 1));
        System.out.println(newCap);
        System.out.println(newCap+"-"+MAX_ARRAY_SIZE+"="+(newCap-MAX_ARRAY_SIZE));
		if (newCap - MAX_ARRAY_SIZE > 0) {    // possible overflow
			int minCap = oldCap + 1;
			if (minCap < 0 || minCap > MAX_ARRAY_SIZE)
					throw new OutOfMemoryError();
			newCap = MAX_ARRAY_SIZE;
		}
		System.out.println(newCap);
    }
    
    private static class Product implements Delayed { 
        private String name;  
        Product(String name){  
            this.name = name;  
        } 
        
        @Override
    	public int compareTo(Delayed o) {
    		return 1;
    	}

    	@Override
    	public long getDelay(TimeUnit unit) {
    		return unit.convert(System.currentTimeMillis()/1000, TimeUnit.SECONDS);
    	}
    } 
}

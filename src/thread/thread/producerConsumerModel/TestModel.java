package thread.thread.producerConsumerModel;

import java.util.ArrayList;

public class TestModel {

	private final static int initCapacity = 100;
	
	private final static int productorNum = 105; 
	
	private final static int consumerNum = 0;
	
	private final static int startNum = 0;
	
	public static void main(String[] args) {
		Depot depot = new Depot4(initCapacity);
//		for(int i=0;i<startNum;i++){
//			depot.add(new Thing());
//		}
		ArrayList<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < productorNum; i++) {
			Thread thread = new Thread(new Productor(depot, "productor-" + i));
			list.add(thread);
			thread.start();
		}
		while(depot.getCount()<initCapacity){
			
		}
		try{
			Thread.sleep(5000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		depot.clear();
		System.out.println("##############################################");
//		for (int i = productorNum; i < productorNum*2; i++) {
//			Thread thread = new Thread(new Productor(depot, "productor-" + i));
//			list.add(thread);
//			thread.start();
//		}
//		for (int i = 0; i < consumerNum; i++) {
//			Thread thread = new Thread(new Consumer(depot, "consumer-" + i));
//			list.add(thread);
//			thread.start();
//		}
//		for(int i=0;i<list.size();i++){
//			try {
//				list.get(i).join();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		try{
			Thread.sleep(5000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(depot.getCount());
		System.out.println("main end");
		System.out.println("productorNotEmptySignalCount: "+depot.getProductorNotEmptySignalCount());
		System.out.println("productorNotFullSignalCount: "+depot.getProductorNotFullSignalCount());
		System.out.println("consumerNotEmptySignalCount: "+depot.getConsumerNotEmptySignalCount());
		System.out.println("consumerNotFullSignalCount: " +depot.getConsumerNotFullSignalCount());
	}

}
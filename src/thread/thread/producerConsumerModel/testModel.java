package thread.thread.producerConsumerModel;

public class testModel {

	private final static int initCapacity = 3;

	public static void main(String[] args) {
		Depot depot = new Depot(initCapacity);
		for (int i = 0; i < 10; i++) {
			new Thread(new Productor(depot, "productor-" + i)).start();
		}
		for (int i = 0; i < 10; i++) {
			new Thread(new Consumer(depot, "consumer-" + i)).start();
		}
	}

}
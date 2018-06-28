package thread.thread.producerConsumerModel;

public class Consumer implements Runnable {

	private Depot depot;

	private String name;

	public Consumer(Depot depot, String name) {
		this.depot = depot;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name +" start");
		depot.consume(name);
		System.out.println(name +" end");
	}
}

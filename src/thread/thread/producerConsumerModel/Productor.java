package thread.thread.producerConsumerModel;

public class Productor implements Runnable {

	private Depot depot;

	private String name;

	public Productor(Depot depot, String name) {
		this.depot = depot;
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name +" start");
		depot.product(name, new Thing());
		System.out.println(name +" end");
	}
}

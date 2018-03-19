package thread.thread.producerConsumerModel;

public class Depot {

	private int num = 0;

	private int maxNum;

	public Depot(int maxNum) {
		this.maxNum = maxNum;
	}

	public synchronized void product(String productorName) {
		while (num >= maxNum) {
			System.out.println("depot is full");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		num++;
		System.out.println(productorName + " product, currentNum: " + num);
		this.notifyAll();
	}

	public synchronized void consume(String consumerName) {
		while (num <= 0) {
			System.out.println("depot is empty");
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		num--;
		System.out.println(consumerName + " consume, currentNum: " + num);
		this.notifyAll();
	}

}

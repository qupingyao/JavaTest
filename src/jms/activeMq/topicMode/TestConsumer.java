package jms.activeMq.topicMode;

public class TestConsumer {

	public static void main(String[] args) {
		Consumer consumer = new Consumer();
		Thread t1 = new Thread(new ConsumerThread(consumer, "t1"));
		Thread t2 = new Thread(new ConsumerThread(consumer, "t2"));
		Thread t3 = new Thread(new ConsumerThread(consumer, "t3"));
		t1.start();
		t2.start();
		t3.start();
	}

}

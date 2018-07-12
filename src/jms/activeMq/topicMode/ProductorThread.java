/*package jms.activeMq.topicMode;

public class ProductorThread implements Runnable {

	private Productor productor;
	
	private String queueName;

	public ProductorThread(Productor productor,String queueName) {
		this.productor = productor;
		this.queueName = queueName;
	}

	@Override
	public void run() {
		productor.sendMessage(queueName);
	}

}
*/
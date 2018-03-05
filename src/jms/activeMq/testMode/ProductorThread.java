package jms.activeMq.testMode;

import javax.jms.JMSException;

public class ProductorThread implements Runnable {

	private Productor productor;
	
	private String queueName;

	public ProductorThread(Productor productor,String queueName) {
		this.productor = productor;
		this.queueName = queueName;
	}

	@Override
	public void run() {
		try {
			productor.sendMessage(queueName);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

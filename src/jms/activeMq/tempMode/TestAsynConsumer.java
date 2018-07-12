/*package jms.activeMq.tempMode;

import javax.jms.JMSException;

public class TestAsynConsumer {

	public static void main(String[] args) throws InterruptedException, JMSException {
		
		AsynConsumer consumer1 = new AsynConsumer("1", "queue1",false);
		AsynConsumer consumer2 = new AsynConsumer("2", "queue1",true);
		System.out.println("asynConsumer start listen");
		consumer1.receive();
		Thread.sleep(5000);
		consumer2.receive();
		System.out.println("asynConsumer start listen");

	}

}*/
/*package jms.activeMq.tempMode;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {
	
	private static final String BROKEN_URL = "tcp://localhost:61616";
	
	private ConnectionFactory connectionFactory;

	private Connection connection;
	
	private Session session;
	
	private MessageProducer producer;
	
	public Producer(String queueName){
		try {
			connectionFactory = new ActiveMQConnectionFactory(BROKEN_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			producer = session.createProducer(destination);
//			System.out.println(producer.getDeliveryMode());
//			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendText(String message) {
		try {
			TextMessage text = session.createTextMessage(message);
//			long a = System.currentTimeMillis();
			producer.send(text);
//			long b = System.currentTimeMillis();
//			System.out.println(b-a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void submit() {
//		try{
//			session.commit();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}

	public void close() {
		try {
			if (producer != null){
				producer.close();
			}
			if (session != null){
				session.close();
			}
			if (connection != null){
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
*/
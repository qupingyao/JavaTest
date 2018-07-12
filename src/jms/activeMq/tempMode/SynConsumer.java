/*package jms.activeMq.tempMode;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SynConsumer {

	private static final String BROKEN_URL = "tcp://localhost:61616";

	private String name;

	private ConnectionFactory connectionFactory;

	private Connection connection;

	private Session session;

	private MessageConsumer consumer;

	public SynConsumer(String name, String queueName) {
		try {
			connectionFactory = new ActiveMQConnectionFactory(BROKEN_URL);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			consumer = session.createConsumer(destination);
			this.name = name;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void recive(boolean isAcknowledge) {
		try {
			System.out.println("consumer(" + name + "):->Begin listening...");
			int count = 0;
			while (count < 10) {
				Message message = consumer.receive();
				System.out.println("consumer(" + name + ") receive msg:" + ((TextMessage) message).getText());
				count++;
				if(isAcknowledge){
					message.acknowledge();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void submit() {
		try {
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if (consumer != null) {
				consumer.close();
			}
			if (session != null) {
				session.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/
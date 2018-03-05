package jms.activeMq.tempMode;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class AsynConsumer implements MessageListener {
	
	private static final String BROKEN_URL = "tcp://localhost:61616";

	private String name;

	private ConnectionFactory connectionFactory;

	private Connection connection;

	private Session session;

	private MessageConsumer consumer;
	
	private boolean isAcknowledge;

	public AsynConsumer(String name, String queueName,boolean isAcknowledge) {
		try {
			connectionFactory = new ActiveMQConnectionFactory(BROKEN_URL);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			consumer = session.createConsumer(destination);
			this.name = name;
			this.isAcknowledge = isAcknowledge;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void receive() {
		try {
			System.out.println("Consumer(" + name + "):->Begin listening...");
			consumer.setMessageListener(this); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof TextMessage) {
				TextMessage txtMsg = (TextMessage) message;
				System.out.println("consumer(" + name + ")异步 receive:" + txtMsg.getText());
				if(isAcknowledge){
					message.acknowledge();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void submit() {
//		try {
//			session.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

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
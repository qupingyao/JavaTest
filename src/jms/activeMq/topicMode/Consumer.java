package jms.activeMq.topicMode;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.Connection;
import javax.jms.Queue;

public class Consumer {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

	private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	private ConnectionFactory connectionFactory;

	private Connection connection;

	private Session session;

	private ThreadLocal<MessageConsumer> threadLocal = new ThreadLocal<>();

	public Consumer() {
		try {
			connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
			connection = connectionFactory.createConnection();
			connection.start();//开启传输
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void getMessage(String topicName) {
		try {
			Topic topic = session.createTopic(topicName);
			MessageConsumer consumer = null;
			if (threadLocal.get() != null) {
				consumer = threadLocal.get();
			} else {
				consumer = session.createConsumer(topic);
				threadLocal.set(consumer);
			}
			TextMessage msg = (TextMessage) consumer.receive();
			if (msg != null) {
				System.out.println("getMessage from topic:" + topicName + ",msg:" + msg.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

package jms.activeMq.testMode;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.spi.ThrowableRendererSupport;

import javax.jms.Connection;
import javax.jms.Queue;

public class Productor {

	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;

	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

	private static final String BROKEN_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

	private ConnectionFactory connectionFactory;

	private Connection connection;

	private Session session;

	private ThreadLocal<MessageProducer> threadLocal = new ThreadLocal<>();

	public Productor() {
		try {
			connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEN_URL);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String queueName) throws JMSException {
		try {
			Queue queue = session.createQueue(queueName);
			MessageProducer messageProducer = null;
			if (threadLocal.get() != null) {
				messageProducer = threadLocal.get();
			} else {
				messageProducer = session.createProducer(queue);
				threadLocal.set(messageProducer);
			}
			for (int i = 0; i < 1; i++) {
				TextMessage msg = session.createTextMessage("queueName:" + queueName + ",i:" + i);
				messageProducer.send(msg);
			}
			throw new Exception();
//			session.commit();
//			session.rollback();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
	}
}
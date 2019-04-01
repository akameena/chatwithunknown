package com.uc.unknownchat.serviceimp;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uc.unknownchat.service.MessageSender;

@Service
public class MessageSenderImp implements MessageSender {

	
	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageProducer messageProducer;
	
	@Override
	public void pushInQueue(String msg,String queue) {
		
		
		try {
			String url = ActiveMQConnection.DEFAULT_BROKER_URL;
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);	
			 destination = session.createQueue(queue);
			 messageProducer = session.createProducer(destination);
			 TextMessage textMessage = session.createTextMessage(msg);
			 messageProducer.send(textMessage);
			 connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}

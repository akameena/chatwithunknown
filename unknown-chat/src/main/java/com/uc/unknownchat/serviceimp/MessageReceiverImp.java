package com.uc.unknownchat.serviceimp;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uc.unknownchat.service.MessageReceiver;

@Service
public class MessageReceiverImp  implements MessageReceiver{

	private ConnectionFactory connectionFactory;
	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageConsumer messageConsumer;
	
	@Override
	public  String pullFromQueue(String queue) {
		String msg=null;
		
		try {
			String url = ActiveMQConnection.DEFAULT_BROKER_URL;
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);	
			destination = session.createQueue(queue);
			messageConsumer = session.createConsumer(destination);
			 
			 Message message = messageConsumer.receive(5000);
			 
			 
			 System.out.println(">>>>>>>>2");
			
			 if (message instanceof TextMessage) {
				 
				 TextMessage textMessage = (TextMessage) message;
				 	msg=textMessage.getText();
				}
			 connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}

	
}

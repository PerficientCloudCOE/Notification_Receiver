package com.perficient.cloud.coe.receiver;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import com.perficient.cloud.db.util.HibernateUtil;
import com.perficient.cloud.email.EmailUtil;

public class MessageReceiver {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@RabbitListener(queues = "#{enterpriseQueue.name}")
	public void enterpriseMsgReceiver(Message message){
		System.out.println("Received message from enterprise queue : " + new String(message.getBody()));
		receive(message, "all"); //Call the method you wanted to
	}
	
	@RabbitListener(queues = "#{hrQueue.name}")
	public void hrMsgReceiver(Message message){
		System.out.println("Received message from hr queue : " + new String(message.getBody()));
		receive(message, "HR");
	}
	
	@RabbitListener(queues = "#{pmQueue.name}")
	public void pmMsgReceiver(Message message){
		System.out.println("Received message from pm queue : " + new String(message.getBody()));
		receive(message, "PM");
	}
	
	@RabbitListener(queues = "#{scpgQueue.name}")
	public void scpgMsgReceiver(Message message){
		System.out.println("Received message from scpg queue : " + new String(message.getBody()));
		receive(message, "SEPG");
	}
	
	@RabbitListener(queues = "#{sqaQueue.name}")
	public void sqaMsgReceiver(Message message){
		System.out.println("Received message from sqa queue : " + new String(message.getBody()));
		receive(message, "SQA");
	}
	
	public void receive(Message message, String group){
		HibernateUtil hb= new HibernateUtil();
		Session session = sessionFactory.openSession();
		List<String> emails = hb.getEmails(session, group);
		session.disconnect();
		session.close();
		
		JSONObject msg = new JSONObject(new String(message.getBody()));
		//Send email
		EmailUtil email= new EmailUtil();
		email.setFrom(msg.getString("mailId"));
		email.setContent(msg.getString("message"));
		email.setTo(emails.toArray(new String[0]));
		email.setSubject("Perficient Notifier");
		email.sendEmail();
	}

}

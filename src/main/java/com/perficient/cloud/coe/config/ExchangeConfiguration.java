package com.perficient.cloud.coe.config;

import org.hibernate.SessionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.perficient.cloud.coe.receiver.MessageReceiver;

/**
 * ]
 * 
 * @author naveen
 *
 */
@Configuration
public class ExchangeConfiguration {

	final static String topicExchangeName = "PerficientNotifier";

	final static String enterprise_QName = "prft_all";
	final static String hr_QName = "prft_hr";
	final static String pm_QName = "prft_pm";
	final static String scpg_QName = "prft_sepg";
	final static String sqa_QName = "prft_sqa";

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		connectionFactory.setUri("amqp://rhjgeybk:64DZAP8mrTrOkvjYPlVxjOGzEZ4KrBtV@buck.rmq.cloudamqp.com/rhjgeybk");
		return connectionFactory;
	}

	@Bean
	public SessionFactory getSessionFactory() {
		SessionFactory factory = null;
		if (factory == null)
			factory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
		return factory;
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}

	@Bean
	Queue enterpriseQueue() {
		return new Queue(enterprise_QName);
	}

	@Bean
	Queue hrQueue() {
		return new Queue(hr_QName);
	}

	@Bean
	Queue pmQueue() {
		return new Queue(pm_QName);
	}

	@Bean
	Queue scpgQueue() {
		return new Queue(scpg_QName);
	}

	@Bean
	Queue sqaQueue() {
		return new Queue(sqa_QName);
	}

	@Bean
	Binding enterpriseQBinding(Queue enterpriseQueue, TopicExchange exchange) {
		return BindingBuilder.bind(enterpriseQueue).to(exchange).with("prft.all");
	}

	@Bean
	Binding hrQBinding(Queue hrQueue, TopicExchange exchange) {
		return BindingBuilder.bind(hrQueue).to(exchange).with("prft.hr");
	}

	@Bean
	Binding pmQBinding(Queue pmQueue, TopicExchange exchange) {
		return BindingBuilder.bind(pmQueue).to(exchange).with("prft.pm");
	}

	@Bean
	Binding scpgQBinding(Queue scpgQueue, TopicExchange exchange) {
		return BindingBuilder.bind(scpgQueue).to(exchange).with("prft.scpg");
	}

	@Bean
	Binding sqaQBinding(Queue sqaQueue, TopicExchange exchange) {
		return BindingBuilder.bind(sqaQueue).to(exchange).with("prft.sqa");
	}

	@Bean
	MessageReceiver receiver() {
		return new MessageReceiver();
	}

}

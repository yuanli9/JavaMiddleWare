package com.yuanli.springintegjms.service.impl;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.yuanli.springintegjms.service.ProducerService;

@Component
public class ProducerServiceImpl implements ProducerService{

	private JmsTemplate jmsTemplate;

	public void sendMessage(Destination destination, final String message) {
		System.out.println("message:"+message);
		jmsTemplate.send(destination,new MessageCreator() {
			
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}
		
	public JmsTemplate getJmsTemplate(){
		return jmsTemplate;
	}

	@Resource
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	

}

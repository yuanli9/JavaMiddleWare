package com.yuanli.springintegjms.test;
import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yuanli.springintegjms.service.ProducerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/spring/applicationContext.xml")
public class ProducerConsumerTest {
	
	@Autowired
	private ProducerService producerService;
	
	@Autowired
	@Qualifier("queueDestination")
	private Destination destination;
	
	@Test
	public void testSend(){
		for(int i = 0; i< 4; i++){
			producerService.sendMessage(destination, "这是消息"+(i+1));
		}
	}
}

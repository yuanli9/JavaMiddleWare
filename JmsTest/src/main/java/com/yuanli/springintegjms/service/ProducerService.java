package com.yuanli.springintegjms.service;

import javax.jms.Destination;

public interface ProducerService {
	public void sendMessage(Destination destination, final String message);
}

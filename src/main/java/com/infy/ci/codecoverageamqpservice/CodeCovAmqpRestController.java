package com.infy.ci.codecoverageamqpservice;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
@PropertySource("classpath:application.properties")
@RestController
@Configuration
@RequestMapping("/codecoveragedata")
public class CodeCovAmqpRestController {
	private String replyQueueName;

	private Connection connection;
	private Channel channel;
	private String requestQueueName = "rpc_queue_unit";

	private final Logger logger = LoggerFactory.getLogger(CodeCovAmqpRestController.class);
	private RabbitTemplate rabbitTemplate;

	@Value("${spring.rabbitmq.host}")
	private String host;

	@Autowired
	public CodeCovAmqpRestController() {

	}

	@Autowired
	public void setAllConnection(RabbitTemplate rabbitTemplate) throws IOException, TimeoutException {

		this.rabbitTemplate = rabbitTemplate;
		rabbitTemplate.setReplyTimeout(15_000L);

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(host);
		connection = factory.newConnection();
		channel = connection.createChannel();
		logger.info("Sending2: " + host);

	}

}

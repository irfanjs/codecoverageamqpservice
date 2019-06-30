package com.infy.ci.codecoverageamqpservice;

import java.io.IOException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Component
@Configuration
@RequestMapping("/codecoveragedata")
public class CodeCovAmqpRestController {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${jsa.rabbitmq.exchange}")
	   private String exchange;
	   
	   @Value("${jsa.rabbitmq.routingkey}")
	   private String routingKey;
	   
	   private final Logger logger = LoggerFactory.getLogger(CodeCovAmqpRestController.class);
	   
	@RequestMapping(value = "/{projectid}/cc/aggregate", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String getAggregatedDataForSectionOfNightlyBuild(@PathVariable("projectid") int projectid,
			@RequestParam("buildtype") String buildtype, @RequestParam("build") String build) throws Exception {
		String message = String.format("aggregate" + "-" + projectid + "-" + build + "-" + buildtype);
		logger.info("Sending: " + message);
		Object returned = amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
		logger.info("Reply: " + returned);
		if (returned == null) {
			throw new RuntimeException("failed to get a response for aggregate");
		}
		return returned.toString();
	}
	
	@RequestMapping(value = "/{projectid}/cc/modulewise", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String getModulewiseDataForSectionOfCiBuild(@PathVariable("projectid") int projectid,
			@RequestParam("buildtype") String buildtype, @RequestParam("build") String build) throws Exception {

		String message = String.format("modulewise" + "-" + projectid + "-" + build + "-" + buildtype);
		logger.info("Sending: " + message);
		Object returned = amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
		logger.info("Reply: " + returned);
		if (returned == null) {
			throw new RuntimeException("failed to get a response for modulewise");
		}
		return returned.toString();
	}

	@RequestMapping(value = "/{projectid}/cc/week", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String getWeekDataForSectionBuild(@PathVariable("projectid") int projectid) throws Exception {

		String message = String.format("week" + "-" + projectid);
		logger.info("Sending: " + message);
		Object returned = amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
		logger.info("Reply: " + returned);
		if (returned == null) {
			throw new RuntimeException("failed to get a response for week");
		}
		return returned.toString();
	}
	
	@RequestMapping(value = "/{projectid}/cc/month", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String getMonthDataForSectionBuild(@PathVariable("projectid") int projectid) throws Exception {

		String message = String.format("month" + "-" + projectid);
		logger.info("Sending: " + message);
		Object returned = amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
		logger.info("Reply: " + returned);
		if (returned == null) {
			throw new RuntimeException("failed to get a response for month");
		}
		return returned.toString();
	}

	@RequestMapping(value = "/{projectid}/cc/custom", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String getCustomDataForSectionOfNightlyBuild(@PathVariable("projectid") int projectid,
			@RequestParam("todate") String todate, @RequestParam("fromdate") String fromdate) throws Exception {

		String message = String.format("custom" + "-" + projectid + "-" + todate + "-" + fromdate);
		logger.info("Sending: " + message);
		Object returned = amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
		logger.info("Reply: " + returned);
		if (returned == null) {
			throw new RuntimeException("failed to get a response for custom");
		}
		return returned.toString();
	}
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String getProjectDetails() throws ClassNotFoundException, IOException, SQLException {

		int projectid = 1;

		String message = String.format("projects" + "-" + projectid);
		logger.info("Sending: " + message);
		Object returned = amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
		logger.info("Reply: " + returned);
		if (returned == null) {
			throw new RuntimeException("failed to get a response for projects");
		}
		return returned.toString();

	}

	@RequestMapping(value = "/{projectid}/latestnightlybuilds", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String getLatestAvailableNightlyBuilds(@PathVariable("projectid") int projectid)
			throws Exception {

		String message = String.format("latestnightlybuilds" + "-" + projectid);
		logger.info("Sending: " + message);
		Object returned = amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
		logger.info("Reply: " + returned);
		if (returned == null) {
			throw new RuntimeException("failed to get a response for latestnightlybuilds");
		}
		return returned.toString();

	}

	@RequestMapping(value = "/daterange", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String getdaterange() throws Exception {

		int projectid = 1;

		String message = String.format("daterange" + "-" + projectid);
		logger.info("Sending: " + message);
		Object returned = amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
		logger.info("Reply: " + returned);
		if (returned == null) {
			throw new RuntimeException("failed to get a response for daterange");
		}
		return returned.toString();

	}
	
	@RequestMapping(value = "/{projectid}/cc/{buildnumber}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public @ResponseBody String getbuildnumberwiseinfo(@PathVariable("projectid") int projectid,
			@PathVariable("buildnumber") int buildnumber) throws Exception {

		String message = String.format("buildnumber" + "-" + projectid + "-" + buildnumber);
		logger.info("Sending: " + message);
		Object returned = amqpTemplate.convertSendAndReceive(exchange, routingKey, message);
		logger.info("Reply: " + returned);
		if (returned == null) {
			throw new RuntimeException("failed to get a response for buildnumber");
		}
		return returned.toString();

	}

	
}

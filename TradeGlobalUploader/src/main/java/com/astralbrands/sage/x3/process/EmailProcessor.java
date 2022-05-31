package com.astralbrands.sage.x3.process;

import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import com.astralbrands.sage.x3.dao.X3Repository;

/*
	Processor class to process emails based on the
 */

@Component
public class EmailProcessor implements Processor {

	// Log object to hold information regarding the status of an email
	Logger log = LoggerFactory.getLogger(EmailProcessor.class);

	// Dependency for DB object in Spring container
	@Autowired
	X3Repository x3repository;

	// Local variables initialized with dependency annotations that specify corresponding credentials
	@Value("${smtp.host}")
	private String host;
	@Value("${smtp.port}")
	private String port;
	@Value("${smtp.username}")
	private String userName;
	@Value("${smtp.password}")
	private String password;
	@Value("${smtp.from}")
	private String from;
	@Value("${smtp.to}")
	private String toList;

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * String htmlRecord = exchange.getProperty("htmlReport", String.class); String
		 * franchiseId = exchange.getProperty(FRANCHISE_ID, String.class);
		 */
		if (x3repository.checkWrongDataEntry()) {
			sendEmail("Franchise report", "", "");
		}
		/*
		 * exchange.getMessage().setBody(htmlRecord);
		 * exchange.getMessage().setHeader(Exchange.FILE_NAME, franchiseId +
		 * File.separator + HTML_FILE_NAME);
		 */
	}

	/*
		Method to send an email regarding international products' database attributes
	 */
	public void sendEmail(String subject, String body, String franchiseId) {

		log.info("sending .........");

		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost(host);
		javaMailSender.setPort(587);

		javaMailSender.setUsername(userName);
		javaMailSender.setPassword(password);

		Properties props = javaMailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.starttls.required", "true");
		props.put("mail.smtpClient.EnableSsl", "false");
		props.put("mail.debug", "true");
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		String[] to;
		if (toList != null && toList.contains(";")) {
			to = toList.split(";");
		} else {
			to = new String[1];
			to[0] = toList;
		}
		simpleMailMessage.setFrom(from);
		simpleMailMessage
				.setText("Hi Team, \n Please refresh the international products report to view the exceptions \n ");
		simpleMailMessage.setTo(to);

		javaMailSender.send(simpleMailMessage);
		System.out.println("email send with subject : " + subject);
	}

}

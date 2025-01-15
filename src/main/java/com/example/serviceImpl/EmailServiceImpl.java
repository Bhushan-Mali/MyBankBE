package com.example.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.EmailDTO;
import com.example.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	
	@Autowired
	JavaMailSender javaMailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);


	@Override
	public void sendEmail(EmailDTO emailDto, MultipartFile attachment) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setFrom(emailDto.getFrom());
		helper.setTo(emailDto.getTo());
		helper.setCc(emailDto.getCc());
		helper.setSubject(emailDto.getSubject());
		helper.setText(emailDto.getBody());
		
		// needs to handle the null attachment error 
		if(attachment != null) {
			String filename = attachment.getOriginalFilename();
			helper.addAttachment(filename, attachment);
		}		
		javaMailSender.send(mimeMessage);
		logger.info("Email sent successfully");
	}
	
	
	// send email with attachment
	public void sendEmail(String from, String to, String subject, String body, String cc[], MultipartFile attachment) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setFrom(from);
		helper.setTo(to);		
		helper.setCc(cc);
		helper.setSubject(subject);
		helper.setText(body);
		
		//check attachment is null or not
		if(attachment != null) {
			String filename = attachment.getOriginalFilename();
			helper.addAttachment(filename, attachment);
		}
		
		javaMailSender.send(mimeMessage);
		logger.info("Email sent successfully");
	}
	
	
	// send email without an attachment
	public void sendEmail(String from, String to, String subject, String body, String cc[]) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		
		helper.setFrom(from);
		helper.setTo(to);		
		helper.setCc(cc);
		helper.setSubject(subject);
		helper.setText(body);
		
		javaMailSender.send(mimeMessage);
		logger.info("Email sent successfully");
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}

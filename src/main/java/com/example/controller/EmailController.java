package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.dto.EmailDTO;
import com.example.service.EmailService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;

@RestController
@Tag(name="Email", description = "Email Service")
public class EmailController {

	
	@Autowired
	EmailService emailService;
	
	
	@PostMapping("/send-email")
	public void sendEmailWithAttachment(@RequestPart EmailDTO emailDto, @RequestPart MultipartFile attachment) throws MessagingException {
		emailService.sendEmail(emailDto, attachment);
	}
}

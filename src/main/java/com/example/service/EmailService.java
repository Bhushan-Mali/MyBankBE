package com.example.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.dto.EmailDTO;

import jakarta.mail.MessagingException;

public interface EmailService {

	void sendEmail(EmailDTO emailDto, MultipartFile attachment) throws MessagingException;

	

	
}

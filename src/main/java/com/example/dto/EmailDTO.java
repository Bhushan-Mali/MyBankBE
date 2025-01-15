package com.example.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {

	private String from;
	private String to;
	private String subject;
	private String body;
	private String cc[];
	private MultipartFile attachment;
	
}

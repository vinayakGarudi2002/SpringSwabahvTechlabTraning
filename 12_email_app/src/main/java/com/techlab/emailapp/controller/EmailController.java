package com.techlab.emailapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlab.emailapp.dto.EmailBody;
import com.techlab.emailapp.dto.EmailResponseDto;
import com.techlab.emailapp.service.EmailService;

@RestController
@RequestMapping("/emailapp")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	@PostMapping("/email")
	public ResponseEntity<EmailResponseDto> sendEmail(@RequestParam String email , @RequestBody EmailBody emailBody){
		if(emailBody.getAttachmentPath()!=null) {
			return ResponseEntity.ok(emailService.sendEmailWithAttachment(email, emailBody));
		}
		return ResponseEntity.ok(emailService.sendSimpleEmail(email, emailBody));
	}

}

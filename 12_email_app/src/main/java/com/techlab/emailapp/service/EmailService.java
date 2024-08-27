package com.techlab.emailapp.service;

import com.techlab.emailapp.dto.EmailBody;
import com.techlab.emailapp.dto.EmailResponseDto;

public interface EmailService {
	
	EmailResponseDto sendSimpleEmail(String reciverEmail,EmailBody body);
	
	EmailResponseDto sendEmailWithAttachment(String receiverEmail, EmailBody body);
	

}

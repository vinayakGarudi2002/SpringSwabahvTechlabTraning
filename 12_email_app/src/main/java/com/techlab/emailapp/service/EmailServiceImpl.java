package com.techlab.emailapp.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.techlab.emailapp.dto.EmailBody;
import com.techlab.emailapp.dto.EmailResponseDto;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public EmailResponseDto sendSimpleEmail(String receiverEmail, EmailBody body) {
        EmailResponseDto response = new EmailResponseDto();

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(receiverEmail);
            message.setSubject(body.getSubject());
            message.setText(body.getContent());

            javaMailSender.send(message);

            response.setBody(body);
            response.setStatus("SUCCESS");
            response.setMessage("Email sent successfully to " + receiverEmail);
        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setMessage("Failed to send email: " + e.getMessage());
        }

        return response;
    }

    @Override
    public EmailResponseDto sendEmailWithAttachment(String receiverEmail, EmailBody body) {
    	String attachmentPath=body.getAttachmentPath();
        EmailResponseDto response = new EmailResponseDto();

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(receiverEmail);
            helper.setSubject(body.getSubject());
            helper.setText(body.getContent());

            // Add attachment
            File file = new File(attachmentPath);
            helper.addAttachment(file.getName(), file);

            javaMailSender.send(message);

            response.setBody(body);
            response.setStatus("SUCCESS");
            response.setMessage("Email with attachment sent successfully to " + receiverEmail);
        } catch (MessagingException e) {
            response.setStatus("ERROR");
            response.setMessage("Failed to send email with attachment: " + e.getMessage());
        }

        return response;
    }
}

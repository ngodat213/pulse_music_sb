package com.app.pulse_music_sb.Service;

import com.app.pulse_music_sb.Service.Interface.IMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService implements IMailService {
    @Autowired
    private JavaMailSender mailSender;

    private void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ngodat.it213@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public void SendForgotPassword(String to, String text) {
        sendSimpleMessage(to, "Forgot Password", text);
    }
}

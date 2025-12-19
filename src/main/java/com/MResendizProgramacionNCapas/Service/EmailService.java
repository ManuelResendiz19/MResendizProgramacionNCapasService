
package com.MResendizProgramacionNCapas.Service;

import com.MResendizProgramacionNCapas.Mail.VerificationEmail;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;
    
//    @Value("${spring.mail.username}")
//    private String formEmail;
    
    private final Map<String, String> verificationTokens = new ConcurrentHashMap<>();
    
//    public void sendEmail(String to, String subject, String body){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//        message.setFrom(formEmail);
//        
//        mailSender.send(message);
//    }
    
    public void sendEmailVerification(VerificationEmail verification) {

        String link = "http://localhost:8081/verification?token " + verification.getToken();
        
        SimpleMailMessage message = new SimpleMailMessage();
        
        verificationTokens.put(verification.getEmail(), verification.getToken());
        
        message.setTo(verification.getEmail());
        message.setSubject("Verificacion");
        message.setText("Verifica tu correo con el siguiente Link:\n " + link);
         
        mailSender.send(message);
    }
    
    public boolean verifyToken(String token){
        return verificationTokens.containsValue(token);
    }
    
    public void removeToken(String token){
        verificationTokens.entrySet().removeIf(entry -> entry.getValue().equals(token));
    }
    
}

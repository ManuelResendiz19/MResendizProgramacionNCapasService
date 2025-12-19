//
//package com.MResendizProgramacionNCapas.Mail;
//
//import com.MResendizProgramacionNCapas.Service.EmailService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MessageListener {
////    
////    @Autowired
////    private EmailService emailService;
//    
//    @Autowired
//    private JavaMailSender mailSender;
//    
////    @JmsListener(destination = "miCola")
////    public void reciveMessage(String message){
////        System.out.println("Mensaje Recibido " +  message);
////    }
//    
////     @JmsListener(destination = "miCola")
//    public void receiveMessage(VerificationEmail verification) {
//
//        String link = "http://localhost:8080/verification" + verification.getToken();
//        
//        SimpleMailMessage message = new SimpleMailMessage();
//        
//        message.setTo(verification.getEmail());
//        message.setSubject("Verificacion");
//        message.setText("Verifica tu correo con el siguiente Link: " + link);
//         
//        mailSender.send(message);
//    }
//
//}

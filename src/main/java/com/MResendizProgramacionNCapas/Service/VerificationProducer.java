//
//package com.MResendizProgramacionNCapas.Service;
//
//import com.MResendizProgramacionNCapas.Mail.VerificationEmail;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class VerificationProducer {
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//    
//    
//    public void sendVerification(String email, String token){
////        VerificationEmail verification = new VerificationEmail();
////        verification.setEmail(email);
////        verification.setToken(UUID.randomUUID().toString());
//        VerificationEmail Vemail = new VerificationEmail(email, token);
//        jmsTemplate.convertAndSend("miCola", Vemail);
//    }
//    
//}

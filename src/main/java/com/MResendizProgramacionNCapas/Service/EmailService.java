package com.MResendizProgramacionNCapas.Service;

import com.MResendizProgramacionNCapas.Mail.VerificationEmail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

//    @Value("${spring.mail.username}")
//    private String formEmail;
    private final Map<String, Integer> verificationTokens = new ConcurrentHashMap<>();

//    public void sendEmail(String to, String subject, String body){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(body);
//        message.setFrom(formEmail);
//        
//        mailSender.send(message);
//    }
    public void sendEmailVerification(VerificationEmail verification) throws MessagingException {

        String link = "http://localhost:8081/usuario/VerificationEmail?token=" + verification.getToken();
//        String link = "http://localhost:8080/api/email/verification?token=" + verification.getToken();
//        SimpleMailMessage message = new SimpleMailMessage();
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        verificationTokens.put( verification.getToken(), verification.getIdUsuario());

        helper.setTo(verification.getEmail());
        helper.setSubject("Verifica tu cuenta");

        String html = "<!DOCTYPE html>"
                + "<html lang='es'>"
                + "<head>"
                + "  <meta charset='UTF-8'>"
                + "</head>"
                + "<body style='margin:0; padding:0; background-color:#f8f9fa; font-family: Arial, sans-serif;'>"
                + "  <table width='100%' cellpadding='0' cellspacing='0'>"
                + "    <tr>"
                + "      <td align='center' style='padding:40px 0;'>"
                + "        <table width='600' cellpadding='0' cellspacing='0' style='background:#ffffff; border-radius:8px; box-shadow:0 4px 10px rgba(0,0,0,0.1);'>"
                + "          <tr>"
                + "            <td style='padding:30px; text-align:center;'>"
                + "              <h2 style='color:#343a40; margin-bottom:10px;'>¡Bienvenido!</h2>"
                + "              <p style='color:#6c757d; font-size:16px;'>Gracias por registrarte. Para activar tu cuenta, confirma tu correo electrónico.</p>"
                + "            </td>"
                + "          </tr>"
                + "          <tr>"
                + "            <td align='center' style='padding:20px;'>"
                + "              <a href='" + link + "' "
                + "                 style='background-color:#0d6efd; color:#ffffff; padding:14px 28px; "
                + "                        text-decoration:none; font-size:16px; border-radius:6px; display:inline-block;'>"
                + "                Verificar mi correo"
                + "              </a>"
                + "            </td>"
                + "          </tr>"
                + "          <tr>"
                + "            <td style='padding:20px; text-align:center;'>"
                + "              <p style='color:#adb5bd; font-size:14px;'>Si no creaste esta cuenta, puedes ignorar este mensaje :b.</p>"
                + "            </td>"
                + "          </tr>"
                + "          <tr>"
                + "            <td style='background:#f1f3f5; padding:15px; text-align:center; border-radius:0 0 8px 8px;'>"
                + "              <p style='color:#868e96; font-size:12px; margin:0;'>Verificacion de correo</p>"
                + "            </td>"
                + "          </tr>"
                + "        </table>"
                + "      </td>"
                + "    </tr>"
                + "  </table>"
                + "</body>"
                + "</html>";
//        message.setTo(verification.getEmail());
//        message.setSubject("Verificacion");
//        message.setText("Verifica tu correo con el siguiente link:\n " + link);
        helper.setText(html, true);
        mailSender.send(mimeMessage);
    }

    public boolean verifyToken(String token) {
        return verificationTokens.containsKey(token);
    }

    public int getUserIdToken(String token){
        return verificationTokens.get(token);
    }
    
    public void removeToken(String token) {
        verificationTokens.remove(token);
    }

}

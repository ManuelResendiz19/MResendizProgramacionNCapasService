package com.MResendizProgramacionNCapas.RestController;

import com.MResendizProgramacionNCapas.DAO.UsuarioDAOImplementationJPA;
import com.MResendizProgramacionNCapas.JPA.Result;
import com.MResendizProgramacionNCapas.JPA.UsuarioJPA;
import com.MResendizProgramacionNCapas.Mail.VerificationEmail;
import com.MResendizProgramacionNCapas.Service.EmailService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailRestController {

    @Autowired
    EmailService emailService;

    @Autowired
    private UsuarioDAOImplementationJPA usuarioDAOImplementationJPA;

    @PostMapping("/registro")
    public ResponseEntity<String> send(@RequestParam String to, @RequestParam int IdUsuario) {

        try {
            VerificationEmail verification = new VerificationEmail();
            verification.setEmail(to);
            verification.setIdUsuario(IdUsuario);
            verification.setToken(UUID.randomUUID().toString());

            emailService.sendEmailVerification(verification);

            return ResponseEntity.ok("Correo de Verificacion Enviado");
        } catch (Exception ex) {
            String errorMsg = "Error al enviar correo: " + ex.getMessage();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorMsg);
        }

    }

    @GetMapping("/verification")
    public ResponseEntity<String> sendVerification(@RequestParam("token") String token) {

        int idUsuario = emailService.getUserIdToken(token);

        boolean isValid = emailService.verifyToken(token);

        if (isValid) {
            UsuarioJPA usuario = (UsuarioJPA) usuarioDAOImplementationJPA.GetById(idUsuario).object;
            usuario.setIsVerified(1);
            usuarioDAOImplementationJPA.Update(usuario);
            emailService.removeToken(token);

            return ResponseEntity.ok("Correo electronico verificado con exito");
        } else {
            return ResponseEntity.ok("El token no existe o es invalido");
        }

    }

}

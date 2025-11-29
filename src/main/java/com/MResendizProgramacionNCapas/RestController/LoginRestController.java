    
package com.MResendizProgramacionNCapas.RestController;

import com.MResendizProgramacionNCapas.DTO.LoginRequest;
import com.MResendizProgramacionNCapas.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginRestController {

    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    JwtService jwtService;
    
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
                )
            );

      
        
        
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        String Token = jwtService.generatedToken(userDetails);
        
        return ResponseEntity.ok(Token);
    }
    
    
}

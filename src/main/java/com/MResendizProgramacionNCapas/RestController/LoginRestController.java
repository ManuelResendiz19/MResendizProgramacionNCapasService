    
package com.MResendizProgramacionNCapas.RestController;

import com.MResendizProgramacionNCapas.Login.LoginRequest;
import com.MResendizProgramacionNCapas.Login.LoginResponse;
import com.MResendizProgramacionNCapas.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<LoginResponse>  login(@RequestBody LoginRequest loginRequest){
        try{
        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
                )
            );
 
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
        String rol = userDetails.getAuthorities()
                .iterator().next().getAuthority();
         
        if (rol.startsWith("ROLE_")) {
            rol = rol.substring(5); 
        }
                
        LoginResponse response = jwtService.generateLoginResponse(userDetails, rol);
       

        return ResponseEntity.ok(response);
        }catch(Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
    
    
}

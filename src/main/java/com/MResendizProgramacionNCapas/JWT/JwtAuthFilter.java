package com.MResendizProgramacionNCapas.JWT;

import com.MResendizProgramacionNCapas.Service.JwtService;
import com.MResendizProgramacionNCapas.Service.UsuarioDetailsJPAService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UsuarioDetailsJPAService usuarioDetailsJPAService;
    private final JwtService jwtService;

    private final String AUTH_HEADER = "Authorization";
    private final String AUTH_TYPE = "Bearer ";

    public JwtAuthFilter(UsuarioDetailsJPAService usuarioDetailsJPAService, JwtService jwtService) {
        this.usuarioDetailsJPAService = usuarioDetailsJPAService;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String token = extractAuthorizationHeader(request);
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = usuarioDetailsJPAService.loadUserByUsername(username);

            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken
                        = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final String tokenUser = jwtService.extractUsername(token);

        if (tokenUser != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final UserDetails userDetails = usuarioDetailsJPAService.loadUserByUsername(tokenUser);

            if (!jwtService.isTokenValid(token, userDetails)) {
                throw new UserPrincipalNotFoundException("Fallo la autenticacion y el acceso del token");
            }

            final SecurityContext context = SecurityContextHolder.createEmptyContext();
            final UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            context.setAuthentication(authToken);
            SecurityContextHolder.setContext(context);
        }

        filterChain.doFilter(request, response);

    }

    private String extractAuthorizationHeader(HttpServletRequest request) {
        final String headerValue = request.getHeader(AUTH_HEADER);

        if (headerValue == null || !headerValue.startsWith(AUTH_TYPE)) {
            return null;
        }

        return headerValue.substring(AUTH_TYPE.length()).trim();
    }
}

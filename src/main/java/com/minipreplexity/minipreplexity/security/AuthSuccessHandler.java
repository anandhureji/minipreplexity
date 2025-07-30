package com.minipreplexity.minipreplexity.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    public AuthSuccessHandler(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");

        String token = jwtUtil.generateToken(email);

        // Redirect with token
        response.sendRedirect("http://localhost:8081/login-success?token=" + token + "&email=" + email);


    }
}

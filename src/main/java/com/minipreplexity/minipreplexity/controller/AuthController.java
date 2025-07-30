package com.minipreplexity.minipreplexity.controller;

import com.minipreplexity.minipreplexity.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/success")
    public ResponseEntity<?> loginSuccess(Authentication authentication) {
        OAuth2User user = (OAuth2User) authentication.getPrincipal();

        String email = user.getAttribute("email");
        String name = user.getAttribute("name");

        // You can log or save the user here

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("email", email);
        response.put("name", name);

        return ResponseEntity.ok(response);
    }
}

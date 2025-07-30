package com.minipreplexity.minipreplexity.security;

import com.minipreplexity.minipreplexity.Repo.UserProfileRepository;
import com.minipreplexity.minipreplexity.model.UserProfile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");
        if (!userProfileRepository.existsById(email)) {
            UserProfile user = new UserProfile(email, name, picture);
            userProfileRepository.save(user);
        }

        // redirect to frontend or dashboard
        response.sendRedirect("http://localhost:8081/dashboard");
    }
}

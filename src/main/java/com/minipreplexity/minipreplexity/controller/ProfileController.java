package com.minipreplexity.minipreplexity.controller;


import com.minipreplexity.minipreplexity.Repo.UserProfileRepository;
import com.minipreplexity.minipreplexity.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @GetMapping
    public ResponseEntity<?> getProfile(Authentication authentication) {
        String email = ((OAuth2User) authentication.getPrincipal()).getAttribute("email");
        return userProfileRepository.findById(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<?> updateProfile(@RequestBody UserProfile updatedProfile, Authentication authentication) {
        String email = ((OAuth2User) authentication.getPrincipal()).getAttribute("email");
        updatedProfile.setEmail(email);
        userProfileRepository.save(updatedProfile);
        return ResponseEntity.ok("Profile updated");
    }
}

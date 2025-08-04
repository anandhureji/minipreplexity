package com.minipreplexity.minipreplexity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
@AllArgsConstructor
@Getter
@Setter
public class UserProfile {

    @Id
    private String email; // Use OAuth email as primary key

    private String name;
    private String avatarUrl;
    private String themePreference = "light"; // default

    // Constructors
    public UserProfile() {}
    public UserProfile(String email, String name, String avatarUrl) {
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
    }


}

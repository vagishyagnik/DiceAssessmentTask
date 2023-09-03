package com.dice.tech.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(unique = true)
    private String clientName;
    private String clientSecret;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Client() {}

    public Client(String clientName, String clientSecret) {
        this.clientName = clientName;
        this.clientSecret = encryptClientSecret(clientSecret);
    }

    public Long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = encryptClientSecret(clientSecret);
    }

    // Encrypt the clientSecret
    private String encryptClientSecret(String clientSecret) {
        return passwordEncoder.encode(clientSecret);
    }
    
    public static Boolean isAPasswordMatch(String rawSecret, String encodedSecret) {
        return passwordEncoder.matches(rawSecret, encodedSecret);
    }
}

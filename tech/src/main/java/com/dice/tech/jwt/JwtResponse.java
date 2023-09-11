package com.dice.tech.jwt;

public class JwtResponse {
    private String jwtToken;
    private String clientName;

    public JwtResponse(String jwtToken, String clientName) {
        this.jwtToken = jwtToken;
        this.clientName = clientName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

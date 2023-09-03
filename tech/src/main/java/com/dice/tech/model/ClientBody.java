package com.dice.tech.model;

public class ClientBody {
    private String clientName;
    private String clientSecret;

    public ClientBody(String clientName, String clientSecret) {
        this.clientName = clientName;
        this.clientSecret = clientSecret;
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
        this.clientSecret = clientSecret;
    }

    @Override
    public String toString() {
        return "ClientBody{" +
                "clientName='" + clientName + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                '}';
    }
}

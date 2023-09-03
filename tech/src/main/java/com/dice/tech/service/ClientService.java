package com.dice.tech.service;

import com.dice.tech.model.Client;
import com.dice.tech.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClientService {

    @Autowired
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    private Client findByClientId(Long clientId) {
        return clientRepository.findByClientId(clientId);
    }

    public Client saveClient(String clientName, String clientSecret) {
        Client client = new Client(clientName, clientSecret);
        return clientRepository.save(client);
    }

    public Boolean verifyClient(Long clientId, String clientSecret) {
        Client client = findByClientId(clientId);
        if (client == null) return false;
        return Objects.equals(clientSecret, client.getClientSecret());
    }

    public Boolean verifyClient(String clientName, String clientSecret) {
        Client origClient = clientRepository.findByClientName(clientName);
        return Client.isAPasswordMatch(clientSecret, origClient.getClientSecret());
    }

    public List<String> getDistinctClientNames() {
        return clientRepository.findAllUniqueClientName();
    }

    public Client getClientByClientName(String clientName) {
        return clientRepository.findByClientName(clientName);
    }
}
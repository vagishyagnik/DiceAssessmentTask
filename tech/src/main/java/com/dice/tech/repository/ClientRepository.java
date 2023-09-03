package com.dice.tech.repository;

import com.dice.tech.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByClientId(Long clientId);
    Client findByClientName(String clientName);

    @Query(value = "SELECT DISTINCT client_name FROM client", nativeQuery = true)
    List<String> findAllUniqueClientName();
}

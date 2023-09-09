package com.dice.tech.controllers;

import com.dice.tech.model.Client;
import com.dice.tech.model.ClientBody;
import com.dice.tech.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
public class AuthController {

    Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private ClientService clientService;

    @PostMapping("/signup")
    public ResponseEntity<Client> signup(@RequestBody ClientBody clientBody) {
        try {
            return ResponseEntity.ok(this.clientService.saveClient(clientBody.getClientName(), clientBody.getClientSecret()));
        } catch (Exception e) {
            LOGGER.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Client> login(@RequestBody ClientBody clientBody) {
        try {
            Boolean isAVerifiedClient = this.clientService.verifyClient(clientBody.getClientName(), clientBody.getClientSecret());
            if (isAVerifiedClient) {
                return ResponseEntity.ok(this.clientService.getClientByClientName(clientBody.getClientName()));
            } else {
                throw new Exception("Not a Valid Client" +  clientBody.toString());
            }
        } catch (Exception e) {
            LOGGER.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/unique-client-names")
    public ResponseEntity<List<String>> uniqueClient() {
        try {
            return ResponseEntity.ok(this.clientService.getDistinctClientNames());
        } catch (Exception e) {
            LOGGER.info(Arrays.toString(e.getStackTrace()));
            return ResponseEntity.internalServerError().build();
        }
    }

}

package com.dice.tech.controllers;

import com.dice.tech.jwt.JwtHelper;
import com.dice.tech.jwt.JwtResponse;
import com.dice.tech.model.Client;
import com.dice.tech.model.ClientBody;
import com.dice.tech.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private JwtHelper helper;

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
    public ResponseEntity<JwtResponse> login(@RequestBody ClientBody clientBody) {
        try {
            Boolean isAVerifiedClient = this.clientService.verifyClient(clientBody.getClientName(), clientBody.getClientSecret());
            if (isAVerifiedClient) {
                Client client = this.clientService.getClientByClientName(clientBody.getClientName());
                String token = this.helper.generateToken(client);

                JwtResponse response = new JwtResponse(token, client.getClientName());
                return new ResponseEntity<>(response, HttpStatus.OK);
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

// 1. I have to expose one more api of signin or login
// have to implement the JWT mechanism
// will throw a jwt access token
// 2. Other weather controller apis will be authenticated by that JWT
}

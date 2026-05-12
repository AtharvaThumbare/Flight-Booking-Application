package com.atharva.flightauthservice.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class Customer {


     @GetMapping
     ResponseEntity<?> getCustomer(@RequestBody String token) {

         return ResponseEntity.ok("Customer endpoint accessed with token: "+token );

     }


}

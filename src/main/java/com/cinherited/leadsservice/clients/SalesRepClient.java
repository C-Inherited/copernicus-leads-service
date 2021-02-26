package com.cinherited.leadsservice.clients;

import com.cinherited.leadsservice.dtos.AuthenticationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("salesrep-service")
public interface SalesRepClient {

    @PostMapping("/salesrep/authenticate")
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

//    @GetMapping("/salesrep/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    CompleteSalesRepDTO getSalesRepById(@PathVariable(name = "id") Integer id);
}

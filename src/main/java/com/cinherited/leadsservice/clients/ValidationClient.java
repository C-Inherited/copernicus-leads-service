package com.cinherited.leadsservice.clients;

import com.cinherited.leadsservice.dtos.AuthenticationRequest;
import com.cinherited.leadsservice.dtos.ValidationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient("validation-service")
public interface ValidationClient {

    @RequestMapping(value = "validation/authenticate", method = RequestMethod.POST)
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

    @PostMapping("validation/email")
    boolean checkIsEmailValid(@RequestBody @Valid ValidationDTO validationDTO, @RequestHeader(value = "Authorization") String authorizationHeader);
}

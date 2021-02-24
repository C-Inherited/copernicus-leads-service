package com.cinherited.leadsservice.clients;

import com.cinherited.leadsservice.dtos.AuthenticationRequest;
import com.cinherited.leadsservice.dtos.ValidationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient("validation-service")
public interface ValidationClient {

    @RequestMapping(value = "validation/authenticate", method = RequestMethod.POST)
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);

    @GetMapping("validation/email")
    boolean checkIsEmailValid(@RequestBody @Valid ValidationDTO validationDTO);
}

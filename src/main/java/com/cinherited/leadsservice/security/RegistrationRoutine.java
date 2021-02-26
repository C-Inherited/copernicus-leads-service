package com.cinherited.leadsservice.security;

import com.cinherited.leadsservice.clients.SalesRepClient;
import com.cinherited.leadsservice.clients.ValidationClient;
import com.cinherited.leadsservice.controllers.impl.LeadController;
import com.cinherited.leadsservice.dtos.AuthenticationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


@Component
public class RegistrationRoutine {
    @Autowired
    ValidationClient validationClient;

    @Autowired
    SalesRepClient salesRepClient;

    public static boolean isValidationRegistered = false;

    public static boolean isSalesRepRegistered = false;

    private static final Logger log = LoggerFactory.getLogger(RegistrationRoutine.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

        @Scheduled(fixedRate = 10000)
    public void checkRegistration() {
            if (!isValidationRegistered){
                CircuitBreaker circuitBreaker = circuitBreakerFactory.create("validation-service");
                log.info("Trying to register with validation-service {}", dateFormat.format(new Date()));
                AuthenticationRequest authenticationRequest = new AuthenticationRequest("leads-service", "leads-service");
                ResponseEntity<?> responseEntity= circuitBreaker.run(() -> validationClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("validation-service"));
                if (responseEntity != null) {
                    parseValidationJWT(responseEntity);
                    isValidationRegistered = true;
                    log.info("Registered with validation-service auth token: {}", LeadController.getValidationAuthOk());
                }
            }
            if (!isSalesRepRegistered){
                CircuitBreaker circuitBreaker = circuitBreakerFactory.create("salesrep-service");
                log.info("Trying to register with salesrep-service {}", dateFormat.format(new Date()));
                AuthenticationRequest authenticationRequest = new AuthenticationRequest("leads-service", "leads-service");
                ResponseEntity<?> responseEntity= circuitBreaker.run(() -> salesRepClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("salesrep-service"));
                if (responseEntity != null) {
                    parseSalesRepJWT(responseEntity);
                    isSalesRepRegistered = true;
                    log.info("Registered with salesrep-service auth token: {}", LeadController.getSalesRepAuthOk());
                }
            }
    }

    private void parseValidationJWT(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        LeadController.setValidationAuthOk(auth.substring(5, auth.length() - 1));
    }

    private void parseSalesRepJWT(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        LeadController.setSalesRepAuthOk(auth.substring(5, auth.length() - 1));
    }

    private ResponseEntity<?> fallbackTransaction(String serviceName) {
        log.info( serviceName + " is not reachable {}", dateFormat.format(new Date()));
        return null;
    }
}

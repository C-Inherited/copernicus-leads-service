package com.cinherited.leadsservice.controllers.impl;

import com.cinherited.leadsservice.controllers.interfaces.ILeadController;
import com.cinherited.leadsservice.dtos.AuthenticationRequest;
import com.cinherited.leadsservice.dtos.AuthenticationResponse;
import com.cinherited.leadsservice.dtos.LeadDTO;
import com.cinherited.leadsservice.security.MyUserDetailsService;
import com.cinherited.leadsservice.services.interfaces.ILeadServices;
import com.cinherited.leadsservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeadController implements ILeadController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    ILeadServices leadServices;


    private static String validationAuthOk;

    private static String salesRepAuthOk;

    @Override
    @GetMapping("/leads/all")
    public List<LeadDTO> findAll(@RequestHeader(value = "Authorization") String authorizationHeader) {
        return leadServices.findAll();
    }

    @Override
    @GetMapping("/leads/all/{salesRepId}")
    public List<LeadDTO> findAllBySalesRepId(@PathVariable int salesRepId) {
        return leadServices.findAllBySalesRepId(salesRepId);
    }

    @Override
    @GetMapping("/leads/{leadId}")
    public LeadDTO findByLeadId(@PathVariable int leadId) {
        return leadServices.findByLeadId(leadId);
    }

    @Override
    @PostMapping("leads/new")
    public LeadDTO createNewLead(@RequestBody LeadDTO leadDTO) {
        return leadServices.createNewLead(leadDTO);
    }

    @Override
    @PutMapping("leads/update")
    public LeadDTO updateLead(@RequestBody LeadDTO leadDTO) {
        return leadServices.updateLead(leadDTO);
    }


    @Override
    @DeleteMapping("leads/delete/{leadId}")
    public int deleteLead(@PathVariable int leadId) {
        return leadServices.deleteLead(leadId);
    }


    @RequestMapping(value = "/leads/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    public static String getValidationAuthOk() {
        return validationAuthOk;
    }

    public static void setValidationAuthOk(String validationAuthOk) {
        LeadController.validationAuthOk = validationAuthOk;
    }

    public static String getSalesRepAuthOk() {
        return salesRepAuthOk;
    }

    public static void setSalesRepAuthOk(String salesRepAuthOk) {
        LeadController.salesRepAuthOk = salesRepAuthOk;
    }
}

package com.cinherited.leadsservice.controllers.impl;

import com.cinherited.leadsservice.controllers.interfaces.ILeadController;
import com.cinherited.leadsservice.dtos.LeadDTO;
import com.cinherited.leadsservice.services.impl.ILeadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeadController implements ILeadController {

    @Autowired
    ILeadServices leadServices;


    @Override
    @GetMapping("/leads/all")
    public List<LeadDTO> findAll() {
        return leadServices.findAll();
    }
}

package com.cinherited.leadsservice.controllers.impl;

import com.cinherited.leadsservice.controllers.interfaces.ILeadController;
import com.cinherited.leadsservice.dtos.LeadDTO;
import com.cinherited.leadsservice.services.impl.ILeadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}

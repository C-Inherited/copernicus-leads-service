package com.cinherited.leadsservice.services.interfaces;

import com.cinherited.leadsservice.dtos.LeadDTO;
import com.cinherited.leadsservice.repositories.LeadRepository;
import com.cinherited.leadsservice.services.impl.ILeadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadServices implements ILeadServices {
    @Autowired
    LeadRepository leadRepository;

    @Override
    public List<LeadDTO> findAll() {
        return leadRepository.findAll().stream().map(lead -> LeadDTO.parseFromLead(lead)).collect(Collectors.toList());
    }
}

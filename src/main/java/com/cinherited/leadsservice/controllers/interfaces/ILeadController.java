package com.cinherited.leadsservice.controllers.interfaces;

import com.cinherited.leadsservice.dtos.LeadDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ILeadController {
    List<LeadDTO> findAll();
    List<LeadDTO> findAllBySalesRepId(int salesRepId);
    LeadDTO findByLeadId(int leadId);
    LeadDTO createNewLead(LeadDTO leadDTO);
    LeadDTO updateLead(LeadDTO leadDTO);
    int deleteLead(int leadId);
}

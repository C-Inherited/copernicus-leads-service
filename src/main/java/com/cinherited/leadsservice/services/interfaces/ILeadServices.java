package com.cinherited.leadsservice.services.interfaces;

import com.cinherited.leadsservice.dtos.LeadDTO;

import java.util.List;

public interface ILeadServices {
    List<LeadDTO> findAll();

    List<LeadDTO> findAllBySalesRepId(int salesRepId);

    LeadDTO findByLeadId(int leadId);

    LeadDTO createNewLead(LeadDTO leadDTO);

    LeadDTO updateLead(LeadDTO leadDTO);

    int deleteLead(int leadId);
}

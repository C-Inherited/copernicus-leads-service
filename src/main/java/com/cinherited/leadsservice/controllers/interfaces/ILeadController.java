package com.cinherited.leadsservice.controllers.interfaces;

import com.cinherited.leadsservice.dtos.LeadDTO;

import java.util.List;

public interface ILeadController {
    List<LeadDTO> findAll();
}

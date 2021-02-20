package com.cinherited.leadsservice.services.impl;

import com.cinherited.leadsservice.dtos.LeadDTO;

import java.util.List;

public interface ILeadServices {
    List<LeadDTO> findAll();
}

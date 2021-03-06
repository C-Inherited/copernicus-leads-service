package com.cinherited.leadsservice.services.impl;

import com.cinherited.leadsservice.clients.ValidationClient;
import com.cinherited.leadsservice.controllers.impl.LeadController;
import com.cinherited.leadsservice.dtos.LeadDTO;
import com.cinherited.leadsservice.dtos.ValidationDTO;
import com.cinherited.leadsservice.enums.ValidationType;
import com.cinherited.leadsservice.models.Lead;
import com.cinherited.leadsservice.repositories.LeadRepository;
import com.cinherited.leadsservice.services.interfaces.ILeadServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadServices implements ILeadServices {
    @Autowired
    LeadRepository leadRepository;

    @Autowired
    ValidationClient validationClient;

    @Override
    public List<LeadDTO> findAll() {
        return leadRepository.findAll().stream().map(lead -> LeadDTO.parseFromLead(lead)).collect(Collectors.toList());
    }

    @Override
    public List<LeadDTO> findAllBySalesRepId(int salesRepId) {
        return leadRepository.getLeadByLeadSalesRepId(salesRepId).stream().map(lead -> LeadDTO.parseFromLead(lead)).collect(Collectors.toList());
    }

    @Override
    public LeadDTO findByLeadId(int leadId) {
        Lead tLead = leadRepository.getLeadByLeadId(leadId);
        if (tLead == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return LeadDTO.parseFromLead(tLead);
    }

    @Override
    public LeadDTO createNewLead(LeadDTO leadDTO) {
        if (!validationClient.checkIsEmailValid(new ValidationDTO(leadDTO.getLeadEmail(), 0, ValidationType.EMAIL), "Bearer "+LeadController.getValidationAuthOk())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!validationClient.checkIsNameValid(new ValidationDTO(leadDTO.getLeadName(), 0, ValidationType.NAME), "Bearer "+LeadController.getValidationAuthOk())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!validationClient.checkIsPhoneNumberValid(new ValidationDTO(leadDTO.getLeadPhone(), 0, ValidationType.PHONE_NUMBER), "Bearer "+LeadController.getValidationAuthOk())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return LeadDTO.parseFromLead(leadRepository.save(Lead.parseFromLeadDTO(leadDTO)));
    }

    @Override
    public LeadDTO updateLead(LeadDTO leadDTO) {
        Lead tLead = leadRepository.getLeadByLeadId(leadDTO.getLeadId());
        if (tLead == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        tLead = Lead.parseFromLeadDTO(leadDTO);
        return LeadDTO.parseFromLead(leadRepository.save(tLead));
    }

    @Override
    public int deleteLead(int leadId) {
        Lead tLead = leadRepository.getLeadByLeadId(leadId);
        if (tLead == null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        leadRepository.delete(tLead);
        return 0;
    }

}

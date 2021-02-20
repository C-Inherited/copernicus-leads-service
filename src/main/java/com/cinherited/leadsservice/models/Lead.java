package com.cinherited.leadsservice.models;

import com.cinherited.leadsservice.dtos.LeadDTO;

import javax.persistence.*;

@Entity
@Table(name = "leads")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leadId;
    private String leadName;
    private String leadPhone;
    private String leadEmail;
    private String leadCompanyName;
    private int leadSalesRepId;

    public Lead() {
    }

    public Lead(String leadName, String leadPhone, String leadEmail, String leadCompanyName, int leadSalesRepId) {
        this.leadName = leadName;
        this.leadPhone = leadPhone;
        this.leadEmail = leadEmail;
        this.leadCompanyName = leadCompanyName;
        this.leadSalesRepId = leadSalesRepId;
    }

    public static Lead parseFromLeadDTO(LeadDTO leadDTO) {
        return new Lead(leadDTO.getLeadName(),
                leadDTO.getLeadPhone(),
                leadDTO.getLeadEmail(),
                leadDTO.getLeadCompanyName(),
                leadDTO.getLeadSalesRepId());
    }

    public int getLeadId() {
        return leadId;
    }

    public void setLeadId(int leadId) {
        this.leadId = leadId;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getLeadPhone() {
        return leadPhone;
    }

    public void setLeadPhone(String leadPhone) {
        this.leadPhone = leadPhone;
    }

    public String getLeadEmail() {
        return leadEmail;
    }

    public void setLeadEmail(String leadEmail) {
        this.leadEmail = leadEmail;
    }

    public String getLeadCompanyName() {
        return leadCompanyName;
    }

    public void setLeadCompanyName(String leadCompanyName) {
        this.leadCompanyName = leadCompanyName;
    }

    public int getLeadSalesRepId() {
        return leadSalesRepId;
    }

    public void setLeadSalesRepId(int leadSalesRepId) {
        this.leadSalesRepId = leadSalesRepId;
    }
}

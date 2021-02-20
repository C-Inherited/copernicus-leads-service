package com.cinherited.leadsservice.dtos;

import com.cinherited.leadsservice.models.Lead;

public class LeadDTO {
    private int leadId;
    private String leadName;
    private String leadPhone;
    private String leadEmail;
    private String leadCompanyName;
    private int leadSalesRepId;

    public LeadDTO() {
    }

    public LeadDTO(int leadId, String leadName, String leadPhone, String leadEmail, String leadCompanyName, int leadSalesRepId) {
        this.leadId = leadId;
        this.leadName = leadName;
        this.leadPhone = leadPhone;
        this.leadEmail = leadEmail;
        this.leadCompanyName = leadCompanyName;
        this.leadSalesRepId = leadSalesRepId;
    }

    public static LeadDTO parseFromLead(Lead lead){
        return new LeadDTO(lead.getLeadId(),
                lead.getLeadName(),
                lead.getLeadPhone(),
                lead.getLeadEmail(),
                lead.getLeadCompanyName(),
                lead.getLeadSalesRepId());
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

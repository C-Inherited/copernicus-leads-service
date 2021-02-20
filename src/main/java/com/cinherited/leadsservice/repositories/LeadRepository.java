package com.cinherited.leadsservice.repositories;

import com.cinherited.leadsservice.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

    Lead getLeadByLeadId(int leadId);

    List<Lead> getLeadByLeadSalesRepId(int salesRepId);

}

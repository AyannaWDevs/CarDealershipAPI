package com.pluralsight.dealership.dealership_api.dao;

import java.util.List;

public interface LeaseContractDao {
    boolean addContract(LeaseContract leaseContract);
    List<LeaseContract> getAllLeaseContracts();
    LeaseContract getContractById(int id);
    boolean updateContractDetails(int id, String details);
    boolean deleteContract(int id);

}

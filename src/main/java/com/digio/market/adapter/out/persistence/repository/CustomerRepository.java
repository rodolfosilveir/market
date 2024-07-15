package com.digio.market.adapter.out.persistence.repository;

import java.util.List;

import com.digio.market.adapter.out.persistence.entity.CustomerEntity;

public interface CustomerRepository {

    List<CustomerEntity> getAllCustomers();
    
}

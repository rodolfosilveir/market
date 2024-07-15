package com.digio.market.application.port.in;

import java.util.List;

import com.digio.market.domain.model.Customer;

public interface CustomerUC {
    
    List<Customer> getTopCustomers();
}

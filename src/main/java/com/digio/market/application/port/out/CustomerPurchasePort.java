package com.digio.market.application.port.out;

import java.util.List;

import com.digio.market.domain.model.Customer;
import com.digio.market.domain.model.CustomerPurchase;

public interface CustomerPurchasePort {

    List<CustomerPurchase> getAllCustomerPurchases();

    List<Customer> getAllCustomers();
    
}

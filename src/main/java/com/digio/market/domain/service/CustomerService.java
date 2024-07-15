package com.digio.market.domain.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digio.market.application.port.in.CustomerUC;
import com.digio.market.application.port.out.CustomerPurchasePort;
import com.digio.market.domain.model.Customer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService implements CustomerUC{

    private final CustomerPurchasePort customerPurchasePort;

    @Override
    public List<Customer> getTopCustomers(){

        List<Customer> allCustomers = customerPurchasePort.getAllCustomers();

        return allCustomers.stream()
                .sorted(Comparator.comparing(Customer::getTotalPurchaseValue)
                        .thenComparing(Customer::getNumberOfPurchases)
                        .reversed())
                .limit(3)
                .collect(Collectors.toList());
        
    }
    
}

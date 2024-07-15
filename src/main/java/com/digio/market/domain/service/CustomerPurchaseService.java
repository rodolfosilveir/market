package com.digio.market.domain.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digio.market.application.exception.ProductNotFoundException;
import com.digio.market.application.port.in.CustomerPurchaseUC;
import com.digio.market.application.port.out.CustomerPurchasePort;
import com.digio.market.domain.model.CustomerPurchase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerPurchaseService implements CustomerPurchaseUC{

    private final CustomerPurchasePort customerPurchasePort;

    @Override
    public List<CustomerPurchase> orderedPurchase() {
        List<CustomerPurchase> customerPurchases = customerPurchasePort.getAllCustomerPurchases();

        return customerPurchases.stream().sorted((cp1, cp2) -> cp1.getTotalValue().compareTo(cp2.getTotalValue()))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerPurchase getBiggestPurchase(Integer year) {

        List<CustomerPurchase> customerPurchases = customerPurchasePort.getAllCustomerPurchases();

        return customerPurchases.stream()
                .filter(cp -> cp.getPurchaseYear().intValue() == year.intValue())
                .max(Comparator.comparing(CustomerPurchase::getTotalValue))
                .orElseThrow(() -> new ProductNotFoundException("Nenhum produto encontrado pelo ano de compra " + year.toString()));
                

    }

}

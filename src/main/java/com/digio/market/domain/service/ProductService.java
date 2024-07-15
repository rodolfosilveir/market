package com.digio.market.domain.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.digio.market.application.exception.CustomerNotFoundException;
import com.digio.market.application.port.in.ProductUC;
import com.digio.market.application.port.out.CustomerPurchasePort;
import com.digio.market.domain.model.CustomerPurchase;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductUC{

    private final CustomerPurchasePort customerPurchasePort;

    @Override
    public CustomerPurchase getWineTypeRecommendation(String cpf) {
        
        List<CustomerPurchase> customerPurchases = customerPurchasePort.getAllCustomerPurchases();

        List<CustomerPurchase> customerPurchasesFiltered = customerPurchases.stream()
            .filter(cp -> cp.getCpf().equals(cpf))
            .collect(Collectors.toList());

        if(customerPurchasesFiltered.isEmpty()){
            throw new CustomerNotFoundException("Cliente não encontrado com o cpf " + cpf);
        }

        String wineTypeRecommendation = customerPurchasesFiltered.stream()
            .collect(Collectors.groupingBy(CustomerPurchase::getWineType, Collectors.summingInt(CustomerPurchase::getAmount)))
            .entrySet().stream()
            .max(Map.Entry.comparingByValue())
            .get().getKey();

        return CustomerPurchase.builder()
            .name(customerPurchasesFiltered.get(0).getName())
            .cpf(cpf)
            .wineType(wineTypeRecommendation)
        .build();
    }
    
}

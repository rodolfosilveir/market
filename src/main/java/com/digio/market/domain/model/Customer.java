package com.digio.market.domain.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Generated
@Builder
@Getter
public class Customer {

    private String name;

    private String cpf;

    private List<Purchase> purchases;

    public BigDecimal getTotalPurchaseValue() {
        return purchases.stream()
                .map(Purchase::getTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getNumberOfPurchases() {
        return purchases.size();
    }
    
}

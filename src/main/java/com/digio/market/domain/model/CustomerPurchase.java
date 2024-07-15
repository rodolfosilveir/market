package com.digio.market.domain.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Generated
@Builder
@Getter
public class CustomerPurchase {

    private String name;

    private String cpf;

    private Integer code;

    private Integer amount;

    private String wineType;

    private BigDecimal price;

    private String harvestYear;
    
    private Integer purchaseYear;

    private BigDecimal totalValue;
    
}

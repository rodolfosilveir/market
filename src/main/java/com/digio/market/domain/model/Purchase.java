package com.digio.market.domain.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Generated
@Builder
@Getter
public class Purchase {

    private Integer code;

    private Integer amount;

    private String wineType;

    private BigDecimal price;

    private String harvestYear;
    
    private Integer purchaseYear;

    private BigDecimal totalValue;
    
}

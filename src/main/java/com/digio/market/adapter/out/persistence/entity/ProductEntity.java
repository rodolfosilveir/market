package com.digio.market.adapter.out.persistence.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Generated
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @JsonProperty("codigo")
    private Integer code;

    @JsonProperty("tipo_vinho")
    private String wineType;

    @JsonProperty("preco")
    private BigDecimal price;

    @JsonProperty("safra")
    private String harvestYear;

    @JsonProperty("ano_compra")
    private Integer purchaseYear;
    
}

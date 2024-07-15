package com.digio.market.adapter.out.persistence.entity;

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
public class PurchaseEntity {

    @JsonProperty("codigo")
    private Integer code;

    @JsonProperty("quantidade")
    private Integer amount;
    
}

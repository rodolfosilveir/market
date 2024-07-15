package com.digio.market.adapter.out.persistence.entity;

import java.util.List;

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
public class CustomerEntity {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("compras")
    private List<PurchaseEntity> purchase;
    
}

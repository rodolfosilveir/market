package com.digio.market.adapter.in.rest.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.digio.market.domain.model.CustomerPurchase;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Generated
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class PurchaseResponse {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("codigo")
    private Integer code;

    @JsonProperty("quantidade")
    private Integer amount;

    @JsonProperty("tipo_vinho")
    private String wineType;

    @JsonProperty("preco")
    private BigDecimal price;

    @JsonProperty("ano_safra")
    private String harvestYear;
    
    @JsonProperty("ano_compra")
    private Integer purchaseYear;

    @JsonProperty("total_compra")
    private BigDecimal totalValue;

    public static List<PurchaseResponse> buildListFromDomain(List<CustomerPurchase> domains){
        return domains.stream().map(PurchaseResponse::buildFromDomain).collect(Collectors.toList());
    }

    public static PurchaseResponse buildFromDomain(CustomerPurchase domain){

        return PurchaseResponse.builder()
            .name(domain.getName())
            .cpf(domain.getCpf())
            .code(domain.getCode())
            .amount(domain.getAmount())
            .wineType(domain.getWineType())
            .price(domain.getPrice())
            .harvestYear(domain.getHarvestYear())
            .purchaseYear(domain.getPurchaseYear())
            .totalValue(domain.getTotalValue())
        .build();
    }
    
}
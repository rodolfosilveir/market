package com.digio.market.adapter.in.rest.response;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.digio.market.domain.model.Customer;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Generated
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class AnalyseCustomerPurchaseResponse {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("numero_de_compras")
    private Integer numberOfPurchases;

    @JsonProperty("valor_total_compras")
    private BigDecimal totalPurchaseValue;

    public static AnalyseCustomerPurchaseResponse buildFromDomain(Customer domain){
        return AnalyseCustomerPurchaseResponse.builder()
            .name(domain.getName())
            .cpf(domain.getCpf())
            .numberOfPurchases(domain.getNumberOfPurchases())
            .totalPurchaseValue(domain.getTotalPurchaseValue())
        .build();
    }

    public static List<AnalyseCustomerPurchaseResponse> buildListFromDomain(List<Customer> domain){
        return domain.stream().map(AnalyseCustomerPurchaseResponse::buildFromDomain).collect(Collectors.toList());
    }
    
}

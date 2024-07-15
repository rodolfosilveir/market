package com.digio.market.adapter.in.rest.response;

import com.digio.market.domain.model.CustomerPurchase;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;

@Generated
@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ProductRecommendationResponse {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("tipo_vinho_recomendado")
    private String wineTypeRecommendation;

    public static ProductRecommendationResponse buildFromDomain(CustomerPurchase domain){
        return ProductRecommendationResponse.builder()
            .name(domain.getName())
            .cpf(domain.getCpf())
            .wineTypeRecommendation(domain.getWineType())
        .build();
    }
    
}

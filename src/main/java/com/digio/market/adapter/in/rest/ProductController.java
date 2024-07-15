package com.digio.market.adapter.in.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.digio.market.adapter.in.rest.response.ProductRecommendationResponse;
import com.digio.market.application.port.in.ProductUC;
import com.digio.market.domain.model.CustomerPurchase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductUC productUC;

    @Operation(summary = "Buscar recomendação de vinho para o cliente")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(hidden = true)))
    @GetMapping(path = "/recomendacao/{cpf}/tipo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductRecommendationResponse> getWineTypeRecommendation(
        @PathVariable(name = "cpf", required = true) final String cpf
    ) {

        CustomerPurchase response = productUC.getWineTypeRecommendation(cpf);

        return ResponseEntity.ok(ProductRecommendationResponse.buildFromDomain(response));
    }
}

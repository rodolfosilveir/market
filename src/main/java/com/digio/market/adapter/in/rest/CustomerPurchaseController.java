package com.digio.market.adapter.in.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.digio.market.adapter.in.rest.response.PurchaseResponse;
import com.digio.market.application.port.in.CustomerPurchaseUC;
import com.digio.market.domain.model.CustomerPurchase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerPurchaseController {

    private final CustomerPurchaseUC customerPurchaseUC;

    @Operation(summary = "Buscar todas as compras ordenadas")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(path = "/compras", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PurchaseResponse>> orderedPurchase() {

        List<CustomerPurchase> response = customerPurchaseUC.orderedPurchase();

        return ResponseEntity.ok(PurchaseResponse.buildListFromDomain(response));
    }

    @Operation(summary = "Buscar a melhor compra do ano")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(hidden = true)))
    @GetMapping(path = "/maior_compra/{year}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PurchaseResponse> biggestPurchase(
        @PathVariable(name = "year", required = true) final Integer year) {

        CustomerPurchase response = customerPurchaseUC.getBiggestPurchase(year);

        return ResponseEntity.ok(PurchaseResponse.buildFromDomain(response));
    }
    
}

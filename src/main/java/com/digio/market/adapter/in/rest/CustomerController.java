package com.digio.market.adapter.in.rest;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digio.market.adapter.in.rest.response.AnalyseCustomerPurchaseResponse;
import com.digio.market.application.port.in.CustomerUC;
import com.digio.market.domain.model.Customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUC customerUC;

    @Operation(summary = "Buscar os 3 melhores clientes")
    @ApiResponse(responseCode = "200", description = "OK")
    @GetMapping(path = "/clientes-fieis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AnalyseCustomerPurchaseResponse>> getTopCustomers() {

        List<Customer> response = customerUC.getTopCustomers();

        return ResponseEntity.ok(AnalyseCustomerPurchaseResponse.buildListFromDomain(response));
    }
    
}

package com.digio.market.adapter.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.digio.market.adapter.in.rest.response.PurchaseResponse;
import com.digio.market.application.port.in.CustomerPurchaseUC;
import com.digio.market.domain.model.CustomerPurchase;

@ExtendWith(MockitoExtension.class)
class CustomerPurchaseControllerTest {

    @InjectMocks
    private CustomerPurchaseController customerPurchaseController;

    @Mock
    private CustomerPurchaseUC customerPurchaseUC;

    private static final Integer YEAR = 2022;

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfullyOrderedPurchase(){

        List<CustomerPurchase> customerPurchases = this.getCustomerPurchasesMock();

        when(customerPurchaseUC.orderedPurchase())
            .thenReturn(customerPurchases);
        
        ResponseEntity<List<PurchaseResponse>> response = customerPurchaseController.orderedPurchase();

        assertNotNull(response);
        List<PurchaseResponse> responses = response.getBody();

        assertNotNull(responses);
        assertEquals(3, responses.size());
        assertTrue(responses.get(0).getTotalValue().doubleValue() < responses.get(1).getTotalValue().doubleValue());
        assertTrue(responses.get(0).getTotalValue().doubleValue() < responses.get(2).getTotalValue().doubleValue());
        assertTrue(responses.get(1).getTotalValue().doubleValue() < responses.get(2).getTotalValue().doubleValue());

    }

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfullyBiggestPurchase(){

        CustomerPurchase customerPurchase = this.getCustomerPurchaseMock();

        when(customerPurchaseUC.getBiggestPurchase(YEAR))
            .thenReturn(customerPurchase);
        
        ResponseEntity<PurchaseResponse> response = customerPurchaseController.biggestPurchase(YEAR);

        assertNotNull(response);
        PurchaseResponse purchaseResponse = response.getBody();

        assertNotNull(purchaseResponse);
        assertEquals(0, YEAR.compareTo(purchaseResponse.getPurchaseYear()));

    }

    private List<CustomerPurchase> getCustomerPurchasesMock(){

        CustomerPurchase customerPurchase1 = CustomerPurchase.builder()
            .name("nome1")
            .cpf("cpf1")
            .code(1)
            .amount(1)
            .wineType("wineType1")
            .price(BigDecimal.valueOf(111L))
            .harvestYear("2021")
            .purchaseYear(YEAR)
            .totalValue(BigDecimal.valueOf(111L).multiply(BigDecimal.valueOf(1L)))
        .build();

        CustomerPurchase customerPurchase2 = CustomerPurchase.builder()
            .name("nome2")
            .cpf("cpf2")
            .code(2)
            .amount(2)
            .wineType("wineType2")
            .price(BigDecimal.valueOf(222L))
            .harvestYear("2022")
            .purchaseYear(YEAR)
            .totalValue(BigDecimal.valueOf(222L).multiply(BigDecimal.valueOf(2L)))
        .build();

        CustomerPurchase customerPurchase3 = CustomerPurchase.builder()
            .name("nome3")
            .cpf("cpf3")
            .code(3)
            .amount(3)
            .wineType("wineType3")
            .price(BigDecimal.valueOf(333L))
            .harvestYear("2023")
            .purchaseYear(YEAR)
            .totalValue(BigDecimal.valueOf(333L).multiply(BigDecimal.valueOf(3L)))
        .build();

        return Arrays.asList(customerPurchase1, customerPurchase2, customerPurchase3);
    }

    private CustomerPurchase getCustomerPurchaseMock(){
        return CustomerPurchase.builder()
            .name("nome3")
            .cpf("cpf3")
            .code(3)
            .amount(3)
            .wineType("wineType3")
            .price(BigDecimal.valueOf(333L))
            .harvestYear("2023")
            .purchaseYear(YEAR)
            .totalValue(BigDecimal.valueOf(333L).multiply(BigDecimal.valueOf(3L)))
        .build();
    }
    
}

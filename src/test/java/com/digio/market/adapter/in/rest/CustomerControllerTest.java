package com.digio.market.adapter.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.digio.market.adapter.in.rest.response.AnalyseCustomerPurchaseResponse;
import com.digio.market.application.port.in.CustomerUC;
import com.digio.market.domain.model.Customer;
import com.digio.market.domain.model.Purchase;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerUC customerUC;

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfullyGetTopCustomers(){

        List<Customer> customers = this.getAllCustomerMock();

        when(customerUC.getTopCustomers())
            .thenReturn(customers);
        
        ResponseEntity<List<AnalyseCustomerPurchaseResponse>> response = customerController.getTopCustomers();

        assertNotNull(response);
        List<AnalyseCustomerPurchaseResponse> responses = response.getBody();

        assertNotNull(responses);
        assertEquals(3, responses.size());
        assertTrue(responses.get(0).getTotalPurchaseValue().doubleValue() > responses.get(1).getTotalPurchaseValue().doubleValue());
        assertTrue(responses.get(0).getTotalPurchaseValue().doubleValue() > responses.get(2).getTotalPurchaseValue().doubleValue());
        assertTrue(responses.get(1).getTotalPurchaseValue().doubleValue() > responses.get(2).getTotalPurchaseValue().doubleValue());

    }

    private List<Customer> getAllCustomerMock(){

        Purchase purchase1 = Purchase.builder()
            .code(1)
            .amount(1)
            .wineType("wineType1")
            .price(BigDecimal.valueOf(111L))
            .harvestYear("2021")
            .purchaseYear(2022)
            .totalValue(BigDecimal.valueOf(111L).multiply(BigDecimal.valueOf(1L)))
        .build();

        List<Purchase> purchases1 = new ArrayList<>();
        purchases1.add(purchase1);

        Customer customer1 = Customer.builder()
            .name("customer1")
            .cpf("cpf1")
            .purchases(purchases1)
        .build();

        Purchase purchase2 = Purchase.builder()
            .code(2)
            .amount(2)
            .wineType("wineType2")
            .price(BigDecimal.valueOf(222L))
            .harvestYear("2022")
            .purchaseYear(2023)
            .totalValue(BigDecimal.valueOf(222L).multiply(BigDecimal.valueOf(2L)))
        .build();

        List<Purchase> purchases2 = new ArrayList<>();
        purchases2.add(purchase2);

        Customer customer2 = Customer.builder()
            .name("customer2")
            .cpf("cpf2")
            .purchases(purchases2)
        .build();

        Purchase purchase3 = Purchase.builder()
            .code(3)
            .amount(3)
            .wineType("wineType3")
            .price(BigDecimal.valueOf(333L))
            .harvestYear("2023")
            .purchaseYear(2024)
            .totalValue(BigDecimal.valueOf(333L).multiply(BigDecimal.valueOf(3L)))
        .build();

        List<Purchase> purchases3 = new ArrayList<>();
        purchases3.add(purchase3);

        Customer customer3 = Customer.builder()
            .name("customer3")
            .cpf("cpf3")
            .purchases(purchases3)
        .build();

        return Arrays.asList(customer3, customer2, customer1);
    }
    
}

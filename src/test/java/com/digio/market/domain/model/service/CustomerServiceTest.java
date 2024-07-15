package com.digio.market.domain.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.digio.market.application.port.out.CustomerPurchasePort;
import com.digio.market.domain.model.Customer;
import com.digio.market.domain.model.Purchase;
import com.digio.market.domain.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerPurchasePort customerPurchasePort;

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfullyGetTopCustomers(){

        List<Customer> customers = this.getAllCustomerMock();

        when(customerPurchasePort.getAllCustomers())
            .thenReturn(customers);
        
        List<Customer> response = customerService.getTopCustomers();

        assertNotNull(response);
        assertEquals(3, response.size());

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


        Purchase purchase4 = Purchase.builder()
            .code(4)
            .amount(4)
            .wineType("wineType4")
            .price(BigDecimal.valueOf(444L))
            .harvestYear("2024")
            .purchaseYear(2025)
            .totalValue(BigDecimal.valueOf(444L).multiply(BigDecimal.valueOf(4L)))
        .build();

        List<Purchase> purchases4 = new ArrayList<>();
        purchases3.add(purchase4);

        Customer customer4 = Customer.builder()
            .name("customer4")
            .cpf("cpf4")
            .purchases(purchases4)
        .build();

        return Arrays.asList(customer1, customer2, customer3, customer4);
    }
    
}

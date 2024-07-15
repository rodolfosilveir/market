package com.digio.market.domain.model.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

import com.digio.market.application.exception.ProductNotFoundException;
import com.digio.market.application.port.out.CustomerPurchasePort;
import com.digio.market.domain.model.CustomerPurchase;
import com.digio.market.domain.service.CustomerPurchaseService;

@ExtendWith(MockitoExtension.class)
class CustomerPurchaseServiceTest {

    @InjectMocks
    private CustomerPurchaseService customerPurchaseService;

    @Mock
    private CustomerPurchasePort customerPurchasePort;

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfullyOrderedPurchase(){

        List<CustomerPurchase> customerPurchases = this.getCustomerPurchasesMock();

        when(customerPurchasePort.getAllCustomerPurchases())
            .thenReturn(customerPurchases);
        
        List<CustomerPurchase> response = customerPurchaseService.orderedPurchase();

        assertNotNull(response);
        assertEquals(3, response.size());
        assertEquals(1, response.get(0).getCode());
        assertEquals(2, response.get(1).getCode());
        assertEquals(3, response.get(2).getCode());

    }

    @Test
    @DisplayName("Deve retornar ProductNotFoundException pois não encontrou alguma compra no ano")
    void shouldThrowProductNotFoundExceptionGetBiggestPurchase(){

        Integer year = 2015;

        List<CustomerPurchase> customerPurchases = this.getCustomerPurchasesMock();

        when(customerPurchasePort.getAllCustomerPurchases())
            .thenReturn(customerPurchases);
        
            ProductNotFoundException e = assertThrows(ProductNotFoundException.class,
            () -> customerPurchaseService.getBiggestPurchase(year)
        );

        assertEquals("Nenhum produto encontrado pelo ano de compra " + year.toString(), e.getMessage());

    }

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfullyGetBiggestPurchase(){

        Integer year = 2022;

        List<CustomerPurchase> customerPurchases = this.getCustomerPurchasesMock();

        when(customerPurchasePort.getAllCustomerPurchases())
            .thenReturn(customerPurchases);
        
        CustomerPurchase response = customerPurchaseService.getBiggestPurchase(year);

        assertNotNull(response);
        assertEquals(year, response.getPurchaseYear().intValue());

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
            .purchaseYear(2022)
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
            .purchaseYear(2022)
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
            .purchaseYear(2024)
            .totalValue(BigDecimal.valueOf(333L).multiply(BigDecimal.valueOf(3L)))
        .build();

        return Arrays.asList(customerPurchase3, customerPurchase1, customerPurchase2);
    }
    
}

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

import com.digio.market.application.exception.CustomerNotFoundException;
import com.digio.market.application.port.out.CustomerPurchasePort;
import com.digio.market.domain.model.CustomerPurchase;
import com.digio.market.domain.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private CustomerPurchasePort customerPurchasePort;

    @Test
    @DisplayName("Deve retornar CustomerNotFoundException pois não encontrou o cliente pelo cpf")
    void shouldThrowCustomerNotFoundExceptionWineTypeRecommendation(){

        String cpf = "11987654321";

        List<CustomerPurchase> customerPurchases = this.getCustomerPurchasesMock();

        when(customerPurchasePort.getAllCustomerPurchases())
            .thenReturn(customerPurchases);

        CustomerNotFoundException e = assertThrows(CustomerNotFoundException.class,
            () -> productService.getWineTypeRecommendation(cpf)
        );

        assertEquals("Cliente não encontrado com o cpf " + cpf, e.getMessage());
    }

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfullyWineTypeRecommendation(){

        String cpf = "12345678911";

        List<CustomerPurchase> customerPurchases = this.getCustomerPurchasesMock();

        when(customerPurchasePort.getAllCustomerPurchases())
            .thenReturn(customerPurchases);
        
        CustomerPurchase response = productService.getWineTypeRecommendation(cpf);

        assertNotNull(response);
        CustomerPurchase customerPurchase = customerPurchases.get(0);
        assertEquals(customerPurchase.getName(), response.getName());
        assertEquals(customerPurchase.getCpf(), response.getCpf());
        assertEquals(customerPurchase.getWineType(), response.getWineType());

    }

    private List<CustomerPurchase> getCustomerPurchasesMock(){

        CustomerPurchase customerPurchase = CustomerPurchase.builder()
            .name("nome")
            .cpf("12345678911")
            .code(1)
            .amount(3)
            .wineType("Rose")
            .price(BigDecimal.valueOf(120D))
            .harvestYear("2020")
            .purchaseYear(2021)
            .totalValue(BigDecimal.valueOf(120D).multiply(BigDecimal.valueOf(3L)))
        .build();

        return Arrays.asList(customerPurchase);
    }
    
}

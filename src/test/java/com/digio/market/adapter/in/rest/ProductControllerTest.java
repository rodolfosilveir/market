package com.digio.market.adapter.in.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.digio.market.adapter.in.rest.response.ProductRecommendationResponse;
import com.digio.market.application.port.in.ProductUC;
import com.digio.market.domain.model.CustomerPurchase;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductUC productUC;

    private static final String CPF = "12345678911";

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfullyWineTypeRecommendation(){

        CustomerPurchase customerPurchase = this.getCustomerPurchaseMock();

        when(productUC.getWineTypeRecommendation(CPF))
            .thenReturn(customerPurchase);
        
        ResponseEntity<ProductRecommendationResponse> response = productController.getWineTypeRecommendation(CPF);

        assertNotNull(response);
        ProductRecommendationResponse productRecommendationResponse = response.getBody();
        assertNotNull(productRecommendationResponse);
        assertEquals(CPF, productRecommendationResponse.getCpf());
        assertEquals("Rose", productRecommendationResponse.getWineTypeRecommendation());

    }

    private CustomerPurchase getCustomerPurchaseMock(){

        return CustomerPurchase.builder()
            .name("nome")
            .cpf(CPF)
            .wineType("Rose")
        .build();
    }
    
}

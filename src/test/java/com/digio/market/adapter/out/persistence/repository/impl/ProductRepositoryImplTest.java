package com.digio.market.adapter.out.persistence.repository.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.digio.market.adapter.out.persistence.entity.ProductEntity;
import com.digio.market.application.exception.ReadProductsException;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {

    @InjectMocks
    private ProductRepositoryImpl productRepositoryImpl;

    @Test
    @DisplayName("Deve retornar ReadProductsException pois não obteve sucesso na leitura dos dados")
    void shouldThrowReadProductsException(){
        ReflectionTestUtils.setField(productRepositoryImpl, "productsJsonFilePath", "/data/error.json");
        ReadProductsException e = assertThrows(ReadProductsException.class,
            () -> productRepositoryImpl.getAllProducts()
        );
        assertEquals("Erro ao obter os produtos", e.getMessage());
    }

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfully(){

        ReflectionTestUtils.setField(productRepositoryImpl, "productsJsonFilePath", "/data/products.json");
        List<ProductEntity> productEntities = productRepositoryImpl.getAllProducts();
        
        assertNotNull(productEntities);
    }
    
}

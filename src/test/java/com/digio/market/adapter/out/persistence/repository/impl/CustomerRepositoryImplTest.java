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

import com.digio.market.adapter.out.persistence.entity.CustomerEntity;
import com.digio.market.application.exception.ReadCustomerException;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryImplTest {

    @InjectMocks
    private CustomerRepositoryImpl customerRepositoryImpl;

    @Test
    @DisplayName("Deve retornar ReadCustomerException pois não obteve sucesso na leitura dos dados")
    void shouldThrowReadCustomerException(){
        ReflectionTestUtils.setField(customerRepositoryImpl, "customersJsonFilePath", "/data/error.json");
        ReadCustomerException e = assertThrows(ReadCustomerException.class,
            () -> customerRepositoryImpl.getAllCustomers()
        );
        assertEquals("Erro ao obter os clientes e compras", e.getMessage());
    }

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfully(){

        ReflectionTestUtils.setField(customerRepositoryImpl, "customersJsonFilePath", "/data/customers.json");
        List<CustomerEntity> customerEntities = customerRepositoryImpl.getAllCustomers();
        
        assertNotNull(customerEntities);
    }
    
}

package com.digio.market.adapter.out.persistence.repository.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.digio.market.adapter.out.persistence.entity.CustomerEntity;
import com.digio.market.adapter.out.persistence.repository.CustomerRepository;
import com.digio.market.application.exception.ReadCustomerException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerRepositoryImpl implements CustomerRepository{

    @Value("${data.jsons.customers-path}")
    private String customersJsonFilePath;

    @Override
    public List<CustomerEntity> getAllCustomers() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream(customersJsonFilePath);
            return objectMapper.readValue(inputStream, new TypeReference<List<CustomerEntity>>() {});
        } catch (Exception e) {
            log.error("Erro ao obter os clientes e compras. BasePath: " + customersJsonFilePath, e);
            throw new ReadCustomerException("Erro ao obter os clientes e compras");
        }
    }

    
    
}

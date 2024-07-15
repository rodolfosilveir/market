package com.digio.market.adapter.out.persistence.repository.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.digio.market.adapter.out.persistence.entity.ProductEntity;
import com.digio.market.adapter.out.persistence.repository.ProductRepository;
import com.digio.market.application.exception.ReadProductsException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProductRepositoryImpl implements ProductRepository{

    @Value("${data.jsons.products-path}")
    private String productsJsonFilePath;

    @Override
    public List<ProductEntity> getAllProducts() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream(productsJsonFilePath);
            return objectMapper.readValue(inputStream, new TypeReference<List<ProductEntity>>() {});
        } catch (Exception e) {
            log.error("Erro ao obter os produtos. BasePath: " + productsJsonFilePath, e);
            throw new ReadProductsException("Erro ao obter os produtos");
        }
    }
    
}

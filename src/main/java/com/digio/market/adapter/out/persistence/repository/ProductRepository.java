package com.digio.market.adapter.out.persistence.repository;

import java.util.List;

import com.digio.market.adapter.out.persistence.entity.ProductEntity;

public interface ProductRepository {

    List<ProductEntity> getAllProducts();
    
}

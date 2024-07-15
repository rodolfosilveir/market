package com.digio.market.adapter.out.persistence;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.digio.market.adapter.out.persistence.entity.CustomerEntity;
import com.digio.market.adapter.out.persistence.entity.ProductEntity;
import com.digio.market.adapter.out.persistence.repository.CustomerRepository;
import com.digio.market.adapter.out.persistence.repository.ProductRepository;
import com.digio.market.application.port.out.CustomerPurchasePort;
import com.digio.market.domain.model.Customer;
import com.digio.market.domain.model.CustomerPurchase;
import com.digio.market.domain.model.Purchase;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerPurchaseAdapter implements CustomerPurchasePort{

    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerPurchase> getAllCustomerPurchases() {
        List<ProductEntity> products = productRepository.getAllProducts();
        List<CustomerEntity> customers = customerRepository.getAllCustomers();

        return customers.stream()
                .flatMap(customer -> customer.getPurchase().stream()
                        .map(purchase -> {
                            ProductEntity product = products.stream()
                                    .filter(p -> p.getCode().equals(purchase.getCode()))
                                    .findFirst()
                                    .orElse(null);

                            if (product != null) {
                                return CustomerPurchase.builder()
                                        .name(customer.getName())
                                        .cpf(customer.getCpf())
                                        .code(purchase.getCode())
                                        .amount(purchase.getAmount())
                                        .wineType(product.getWineType())
                                        .price(product.getPrice())
                                        .harvestYear(product.getHarvestYear())
                                        .purchaseYear(product.getPurchaseYear())
                                        .totalValue(product.getPrice().multiply(BigDecimal.valueOf(purchase.getAmount())))
                                        .build();
                            } else {
                                return null;
                            }
                        }))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> getAllCustomers(){
        List<ProductEntity> products = productRepository.getAllProducts();
        List<CustomerEntity> customers = customerRepository.getAllCustomers();

        return customers.stream()
        .map(customerEntity -> {
            List<Purchase> purchaseList = customerEntity.getPurchase().stream()
                    .flatMap(purchaseEntity -> products.stream()
                            .filter(productEntity -> productEntity.getCode().intValue() == purchaseEntity.getCode().intValue())
                            .map(productEntity -> Purchase.builder()
                                    .code(purchaseEntity.getCode())
                                    .amount(purchaseEntity.getAmount())
                                    .wineType(productEntity.getWineType())
                                    .price(productEntity.getPrice())
                                    .harvestYear(productEntity.getHarvestYear())
                                    .purchaseYear(productEntity.getPurchaseYear())
                                    .totalValue(productEntity.getPrice().multiply(BigDecimal.valueOf(purchaseEntity.getAmount())))
                                    .build()))
                    .collect(Collectors.toList());

            return Customer.builder()
                    .name(customerEntity.getName())
                    .cpf(customerEntity.getCpf())
                    .purchases(purchaseList)
                    .build();
        })
        .collect(Collectors.toList());
    }
    
}

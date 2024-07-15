package com.digio.market.adapter.out.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.digio.market.adapter.out.persistence.entity.CustomerEntity;
import com.digio.market.adapter.out.persistence.entity.ProductEntity;
import com.digio.market.adapter.out.persistence.entity.PurchaseEntity;
import com.digio.market.adapter.out.persistence.repository.CustomerRepository;
import com.digio.market.adapter.out.persistence.repository.ProductRepository;
import com.digio.market.domain.model.Customer;
import com.digio.market.domain.model.CustomerPurchase;
import com.digio.market.domain.model.Purchase;

@ExtendWith(MockitoExtension.class)
class CustomerPurchaseAdapterTest {

    @InjectMocks
    private CustomerPurchaseAdapter customerPurchaseAdapter;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("Deve retornar com sucesso sem CustomerPurchase")
    void shouldReturnSuccessfullyWioutCustomerPurchaseGetAllCustomerPurchases(){

        List<ProductEntity> productEntities = this.getProductEntityListMock(1);
        List<CustomerEntity> customerEntities = this.getCustomerEntityListMock(2);

        when(productRepository.getAllProducts())
            .thenReturn(productEntities);

        when(customerRepository.getAllCustomers())
            .thenReturn(customerEntities);

        List<CustomerPurchase> customerPurchases = customerPurchaseAdapter.getAllCustomerPurchases();

        assertNotNull(customerPurchases);
        assertEquals(0, customerPurchases.size());
    }

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfullyGetAllCustomerPurchases(){

        List<ProductEntity> productEntities = this.getProductEntityListMock(1);
        List<CustomerEntity> customerEntities = this.getCustomerEntityListMock(1);

        when(productRepository.getAllProducts())
            .thenReturn(productEntities);

        when(customerRepository.getAllCustomers())
            .thenReturn(customerEntities);

        List<CustomerPurchase> customerPurchases = customerPurchaseAdapter.getAllCustomerPurchases();

        assertNotNull(customerPurchases);
        ProductEntity productEntity = productEntities.get(0);
        CustomerEntity customerEntity = customerEntities.get(0);
        PurchaseEntity purchaseEntity = customerEntity.getPurchase().get(0);
        CustomerPurchase customerPurchase = customerPurchases.get(0);

        assertEquals(customerEntity.getName(), customerPurchase.getName());
        assertEquals(customerEntity.getCpf(), customerPurchase.getCpf());
        assertEquals(purchaseEntity.getCode(), customerPurchase.getCode());
        assertEquals(purchaseEntity.getAmount(), customerPurchase.getAmount());
        assertEquals(productEntity.getWineType(), customerPurchase.getWineType());
        assertEquals(productEntity.getPrice().doubleValue(), customerPurchase.getPrice().doubleValue());
        assertEquals(productEntity.getHarvestYear(), customerPurchase.getHarvestYear());
        assertEquals(productEntity.getPurchaseYear(), customerPurchase.getPurchaseYear());
        assertEquals(productEntity.getPrice().multiply(BigDecimal.valueOf(purchaseEntity.getAmount())).doubleValue(), 
                customerPurchase.getTotalValue().doubleValue());
    }

    @Test
    @DisplayName("Deve retornar com sucesso sem CustomerPurchase")
    void shouldReturnSuccessfullyWithoutCUstomerPurchaseGetAllCustomers(){        

        List<ProductEntity> productEntities = this.getProductEntityListMock(1);
        List<CustomerEntity> customerEntities = this.getCustomerEntityListMock(2);

        when(productRepository.getAllProducts())
            .thenReturn(productEntities);

        when(customerRepository.getAllCustomers())
            .thenReturn(customerEntities);

        List<Customer> customerPurchases = customerPurchaseAdapter.getAllCustomers();

        assertNotNull(customerPurchases);
        assertEquals(0, customerPurchases.get(0).getPurchases().size());
    }

    @Test
    @DisplayName("Deve retornar com sucesso")
    void shouldReturnSuccessfullyGetAllCustomers(){        

        List<ProductEntity> productEntities = this.getProductEntityListMock(1);
        List<CustomerEntity> customerEntities = this.getCustomerEntityListMock(1);

        when(productRepository.getAllProducts())
            .thenReturn(productEntities);

        when(customerRepository.getAllCustomers())
            .thenReturn(customerEntities);

        List<Customer> customerPurchases = customerPurchaseAdapter.getAllCustomers();

        assertNotNull(customerPurchases);
        ProductEntity productEntity = productEntities.get(0);
        CustomerEntity customerEntity = customerEntities.get(0);
        PurchaseEntity purchaseEntity = customerEntity.getPurchase().get(0);
        Customer customer = customerPurchases.get(0);
        Purchase purchase = customer.getPurchases().get(0);

        assertEquals(customerEntity.getName(), customer.getName());
        assertEquals(customerEntity.getCpf(), customer.getCpf());
        assertEquals(purchaseEntity.getCode(), purchase.getCode());
        assertEquals(purchaseEntity.getAmount(), purchase.getAmount());
        assertEquals(productEntity.getWineType(), purchase.getWineType());
        assertEquals(productEntity.getPrice().doubleValue(), purchase.getPrice().doubleValue());
        assertEquals(productEntity.getHarvestYear(), purchase.getHarvestYear());
        assertEquals(productEntity.getPurchaseYear(), purchase.getPurchaseYear());
        assertEquals(productEntity.getPrice().multiply(BigDecimal.valueOf(purchaseEntity.getAmount())).doubleValue(), 
            purchase.getTotalValue().doubleValue());
    }

    private List<ProductEntity> getProductEntityListMock(Integer code){

        ProductEntity productEntity = ProductEntity.builder()
            .code(code)
            .wineType("Rose")
            .price(BigDecimal.valueOf(120L))
            .harvestYear("2020")
            .purchaseYear(2021)
        .build();

        return Arrays.asList(productEntity);
    }

    private List<CustomerEntity> getCustomerEntityListMock(Integer code){

        PurchaseEntity purchaseEntity = PurchaseEntity.builder()
            .code(code)
            .amount(3)
        .build();

        CustomerEntity customerEntity = CustomerEntity.builder()
           .name("nome") 
           .cpf("cpf")
           .purchase(Arrays.asList(purchaseEntity))
        .build();

        return Arrays.asList(customerEntity);
    }
    
}

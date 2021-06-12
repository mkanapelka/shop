package com.max.shop.service;

import com.max.shop.entity.ProductInOrder;
import com.max.shop.repository.ProductInOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductInOrderService {

    private final ProductInOrderRepository productInOrderRepository;

    public ProductInOrder save(ProductInOrder productInOrder){
        return productInOrderRepository.save(productInOrder);
    }

}

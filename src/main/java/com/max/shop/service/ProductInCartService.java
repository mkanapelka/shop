package com.max.shop.service;

import com.max.shop.entity.ProductInCart;
import com.max.shop.repository.ProductInCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductInCartService {

    private final ProductInCartRepository productInCartRepository;

    public ProductInCart saveProductInCart(ProductInCart productInCart){
        return productInCartRepository.save(productInCart);
    }
}

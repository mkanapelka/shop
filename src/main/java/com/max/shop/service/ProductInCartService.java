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

    public ProductInCart findByProductId(Long id){
        return productInCartRepository.findByProductId(id);
    }

    public List<ProductInCart> findAllByCartId(Long id){
        return productInCartRepository.findAllByCartId(id);
    }

    public void removeAllByCartId(Long id){
        productInCartRepository.removeAllByCartId(id);
    }

    public void removeByProductId(Long id){
        productInCartRepository.removeByProductId(id);
    }
}

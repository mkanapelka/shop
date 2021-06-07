package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.CartDto;
import com.max.shop.dto.ProductDto;
import com.max.shop.entity.Cart;
import com.max.shop.entity.Product;
import com.max.shop.exception.ProductNotFoundException;
import com.max.shop.repository.CartRepository;
import com.max.shop.repository.ProductRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MapperService conversionService;

    public CartDto showCart() {
        Cart cart = cartRepository.findCartByUserId(SecurityUtil.getUserId());
        return conversionService.convert(cart, CartDto.class);
    }

    public CartDto addProductInCart(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(ProductNotFoundException::new);
        Cart cart = cartRepository.findCartByUserId(SecurityUtil.getUserId());
        List<Product> productList = cart.getProducts();
        productList.add(product);
        cart.setProducts(productList);
        cart.setQuantityProduct(cart.getQuantityProduct() + 1);
        cart.setTotalCost(cart.getTotalCost() + product.getCost());
        return conversionService.convert(cart, CartDto.class);
    }

    //    TODO: Do it;
    public CartDto removeProductFromCart(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(ProductNotFoundException::new);
        Cart cart = cartRepository.findCartByUserId(SecurityUtil.getUserId());
        List<Product> productList = cart.getProducts();

        if (productList.contains(productDto)) {

        }
        return conversionService.convert(cart, CartDto.class);
    }


}

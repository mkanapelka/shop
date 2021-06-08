package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.CartDto;
import com.max.shop.entity.Cart;
import com.max.shop.entity.Product;
import com.max.shop.entity.ProductInCart;
import com.max.shop.exception.ProductNotFoundException;
import com.max.shop.repository.CartRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ProductInCartService productInCartService;
    private final MapperService conversionService;

    @Transactional
    public CartDto showCart() {
        Cart cart = cartRepository.findCartByUserId(SecurityUtil.getUserId());
        if (cart == null) {
            cart = createEmptyCart();
        }
        return conversionService.convert(cart, CartDto.class);
    }

    @Transactional
    public CartDto addProductInCart(Long id, int quantityProduct) {
        Cart cart = cartRepository.findCartByUserId(SecurityUtil.getUserId());

        if (cart == null) {
            cart = createEmptyCart();
            cartRepository.save(cart);
        }

        ProductInCart productInCart = findOrCreate(cart, id);
        List<ProductInCart> productInCartList = cart.getProductInCarts();

        boolean check = cart.getProductInCarts().stream()
                .anyMatch(pic -> Objects.equals(pic.getProductId(), id));

        if (!check) {
            productInCart.setQuantity(quantityProduct);
            productInCartList.add(productInCart);
            cart.setProductInCarts(productInCartList);
        } else {
            productInCart = findOneProductInCart(cart, id);
            productInCart.setQuantity(productInCart.getQuantity() + quantityProduct);
            productInCartList.add(productInCart);
            cart.setProductInCarts(productInCartList);
        }

        cart.setQuantityProduct(cart.getQuantityProduct() + quantityProduct);
        cart.setTotalCost(cart.getTotalCost()
                + productInCart.getCost() * quantityProduct);
        cartRepository.save(cart);
        return conversionService.convert(cart, CartDto.class);
    }


    @Transactional
    public CartDto removeProductFromCart(Long id, int quantityProduct) {
        Cart cart = cartRepository.findCartByUserId(SecurityUtil.getUserId());
        ProductInCart productInCart = findOneProductInCart(cart, id);
        List<ProductInCart> productInCartList = cart.getProductInCarts();
        ProductInCart newProductInCart = productInCart;

        if (quantityProduct < newProductInCart.getQuantity()) {
            productInCartList.remove(productInCart);
            newProductInCart.setQuantity(newProductInCart.getQuantity() - quantityProduct);
            productInCartList.add(newProductInCart);
        } else {
            productInCartList.remove(productInCart);
            productInCartService.findByProductId(id).setCart(null);
            productInCartService.removeByProductId(id);
        }

        if (cart.getQuantityProduct() - quantityProduct <= 0){
        cart.setQuantityProduct(0);
        } else {
        cart.setQuantityProduct(cart.getQuantityProduct() - quantityProduct);
        }

        if ((cart.getTotalCost() - productInCart.getCost() * quantityProduct) <= 0){
            cart.setTotalCost(0);
        } else {
        cart.setTotalCost(cart.getTotalCost() - productInCart.getCost() * quantityProduct);
        }
        cartRepository.save(cart);
        return conversionService.convert(cart, CartDto.class);
    }


    @Transactional
    public CartDto cleanCart() {

        Cart cart = cartRepository.findCartByUserId(SecurityUtil.getUserId());
        cart.setQuantityProduct(0);
        cart.setTotalCost(0);
        cart.setProductInCarts(Collections.emptyList());
        List<ProductInCart> productInCartList = productInCartService.findAllByCartId(cart.getId());
        for (ProductInCart productInCart : productInCartList) {
            productInCartService.findByProductId(productInCart.getProductId()).setCart(null);
            productInCartService.removeByProductId(productInCart.getProductId());
        }
        cartRepository.save(cart);
        return conversionService.convert(cart, CartDto.class);
    }


    //--------------------------------------
    private ProductInCart findOneProductInCart(Cart cart, Long id) {
        return cart.getProductInCarts()
                .stream().filter(item -> item.getProductId().equals(id))
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
    }

    private Cart createEmptyCart() {
        Cart cart = new Cart();
        cart.setTotalCost(0);
        cart.setUser(SecurityUtil.getUser());
        cart.setQuantityProduct(0);
        cart.setProductInCarts(new ArrayList<>());
        cartRepository.save(cart);
        return cart;
    }

    private ProductInCart findOrCreate(Cart cart, Long id) {
        ProductInCart productInCart = productInCartService.findByProductId(id);
        Product product = productService.findObeById(id);
        if (productInCart == null) {
            productInCart = new ProductInCart();
            productInCart.setQuantity(1);
            productInCart.setProductId(id);
            productInCart.setName(product.getName());
            productInCart.setCost(product.getCost());
            productInCart.setCart(cart);
            productInCartService.saveProductInCart(productInCart);
        }
        return productInCart;
    }

}

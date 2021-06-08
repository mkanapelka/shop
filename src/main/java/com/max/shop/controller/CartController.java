package com.max.shop.controller;

import com.max.shop.dto.CartDto;
import com.max.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CartDto showCart() {
        return cartService.showCart();
    }

    @GetMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public CartDto addProductInCart(@RequestParam Long id, @RequestParam int quantityProduct){
        return cartService.addProductInCart(id,quantityProduct);
    }

    @GetMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    public CartDto removeProductFromCart(@RequestParam Long id, @RequestParam int quantityProduct){
        return cartService.removeProductFromCart(id,quantityProduct);
    }

    @GetMapping("/clean")
    @ResponseStatus(HttpStatus.OK)
    public CartDto cleanCart(){
        return cartService.cleanCart();
    }

}

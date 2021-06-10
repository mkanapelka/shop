package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.CartDto;
import com.max.shop.entity.Cart;
import com.max.shop.entity.Product;
import com.max.shop.entity.ProductInCart;
import com.max.shop.repository.CartRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final ProductInCartService productInCartService;
    private final MapperService conversionService;

    public CartDto showCart() {
        Cart cart = ensureCart();
        return conversionService.convert(cart, CartDto.class);
    }

    @Transactional
    public CartDto addProductInCart(Long id, int quantityProduct) {
        Cart cart = ensureCart();

        ProductInCart productInCart = findOrCreate(cart, id);
        List<ProductInCart> productInCartList = cart.getProductInCarts();

        productInCart.setQuantity(productInCart.getQuantity() + quantityProduct);
        productInCartList.add(productInCart);

        cart.setQuantityProduct(cart.getQuantityProduct() + quantityProduct);
        cart.setTotalCost(cart.getTotalCost() + productInCart.getCost() * quantityProduct);

        cartRepository.save(cart);
        return conversionService.convert(cart, CartDto.class);
    }


    @Transactional
    public CartDto removeProductFromCart(Long productId, int quantityProduct) {
        Cart cart = cartRepository.findCartByUserId(SecurityUtil.getUserId());
        ProductInCart productInCart = cart.getProductInCarts().stream()
            .filter(pic -> Objects.equals(pic.getProductId(), productId))
            .findFirst()
            .orElse(null);

        if (productInCart == null) {
            return conversionService.convert(cart, CartDto.class);
        }

        if (quantityProduct < productInCart.getQuantity()) {
            productInCart.setQuantity(productInCart.getQuantity() - quantityProduct);
        } else {
            cart.getProductInCarts().remove(productInCart);//not sure if we need to do this
        }

        cart.setQuantityProduct(Math.max(cart.getQuantityProduct() - quantityProduct, 0));
        cart.setTotalCost(Math.max((cart.getTotalCost() - productInCart.getCost() * quantityProduct), 0));

        cartRepository.save(cart);
        return conversionService.convert(cart, CartDto.class);
    }


    @Transactional
    public CartDto cleanCart() {

        Cart cart = cartRepository.findCartByUserId(SecurityUtil.getUserId());
        cart.setQuantityProduct(0);
        cart.setTotalCost(0);

        for (int i = 0; i < cart.getProductInCarts().size(); i++) {
            cart.getProductInCarts().remove(i);
        }
        cartRepository.save(cart);

        return conversionService.convert(cart, CartDto.class);
    }


    //--------------------------------------
    private Cart createEmptyCart() {
        Cart cart = new Cart();
        cart.setUser(SecurityUtil.getUser());
        cart.setProductInCarts(new ArrayList<>());
        cartRepository.save(cart);
        return cart;
    }

    private ProductInCart findOrCreate(Cart cart, Long id) {

        return cart.getProductInCarts().stream()
            .filter(pic -> Objects.equals(pic.getProductId(), id))
            .findFirst()
            .orElseGet(() -> {
                Product product = productService.findObeById(id);
                val productInCart = new ProductInCart();
                productInCart.setProductId(id);
                productInCart.setName(product.getName());
                productInCart.setCost(product.getCost());
                productInCart.setCart(cart);
                return productInCartService.saveProductInCart(productInCart);
            });
    }

    private Cart ensureCart() {
        Cart cart = cartRepository.findCartByUserId(SecurityUtil.getUserId());

        if (cart == null) {
            cart = createEmptyCart();
        }
        return cart;
    }

}

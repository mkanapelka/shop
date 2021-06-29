package com.max.shop.service;

import com.max.shop.dto.CartDto;
import com.max.shop.entity.Cart;
import com.max.shop.entity.ProductInCart;
import com.max.shop.entity.User;
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.repository.CartRepository;
import com.max.shop.repository.UserRepository;
import com.max.shop.repository.parent.IntegrationTestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@Sql("/sql/data.sql")
class CartServiceTest extends IntegrationTestBase {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @AfterEach
    void cleanUp() {
        cartRepository.deleteAll();
    }

    @BeforeEach
    void updateContext(){
        User user = userRepository.findById(10001L).orElseThrow(() -> new EntityNotFountException("User"));

        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user,"", user.getAuthorities()));
    }

    @Test
    public void testEnsureCart() {

        Cart cartExp = Cart.builder()
                .totalCost(6000)
                .quantityProduct(5)
                .build();
        Cart cart = cartService.ensureCart();

        assertEquals(cartExp.getQuantityProduct(), cart.getQuantityProduct());
        assertEquals(cartExp.getTotalCost(), cart.getTotalCost());
    }

    @Test
    public void testFindOrCreate(){

        Cart cart = cartService.ensureCart();
        ProductInCart product = cartService.findOrCreate(cart,10001L);
        ProductInCart productExp = ProductInCart.builder()
                .quantity(5)
                .cart(cart)
                .build();

        assertEquals(productExp.getQuantity(), product.getQuantity());
        assertEquals(productExp.getCart(), product.getCart());

    }

    @Test
    public void testCleanCart(){

        Cart cart = cartService.ensureCart();
        assertNotEquals(cart.getQuantityProduct(), 0);
        assertNotEquals(cart.getTotalCost(), 0);

        cartService.cleanCart();
        assertEquals(cart.getQuantityProduct(), 0);
        assertEquals(cart.getTotalCost(), 0);
        assertEquals(cart.getProductInCarts().size(), 0);
    }

    @Test
    public void testAddProductInCart(){

        Cart cart = cartService.ensureCart();
        CartDto cartExp = CartDto.builder()
                .quantityProduct(6)
                .totalCost(7200)
                .build();

        CartDto cartDto = cartService.addProductInCart(10001L,1);
        assertEquals(cartDto.getQuantityProduct(), cartExp.getQuantityProduct());
        assertEquals(cartDto.getTotalCost(), cartExp.getTotalCost());
    }

    @Test
    public void testRemoveProductFromCart(){
        Cart cart = cartService.ensureCart();
        CartDto cartExp = CartDto.builder()
                .quantityProduct(4)
                .totalCost(4800)
                .build();

        CartDto cartDto = cartService.removeProductFromCart(10001L,1);
        assertEquals(cartDto.getQuantityProduct(), cartExp.getQuantityProduct());
        assertEquals(cartDto.getTotalCost(), cartExp.getTotalCost());
    }

}
package com.max.shop.service;

import com.max.shop.dto.OrderDto;
import com.max.shop.dto.request.OrderCriteriaForUserDto;
import com.max.shop.entity.Cart;
import com.max.shop.entity.Order;
import com.max.shop.entity.OrderStatus;
import com.max.shop.entity.User;
import com.max.shop.entity.embeddable.DeliveryMethod;
import com.max.shop.entity.embeddable.OrderDetails;
import com.max.shop.entity.embeddable.PaymentMethod;
import com.max.shop.exception.UserNotFoundException;
import com.max.shop.repository.OrderRepository;
import com.max.shop.repository.UserRepository;
import com.max.shop.repository.parent.IntegrationTestBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql("/sql/data.sql")
public class OrderServiceTest extends IntegrationTestBase {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void updateContext(){
        User user = userRepository.findById(10001L).orElseThrow(UserNotFoundException::new);

        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user,"", user.getAuthorities()));
    }

    @AfterEach
    void cleanUp() {
        orderRepository.deleteAll();
    }


    @Test
    public void testShowOrdersByFilter() {
        Cart cart = cartService.getCart();
        Cart cartExp = Cart.builder()
                .quantityProduct(cart.getQuantityProduct())
                .totalCost(cart.getTotalCost())
                .user(cart.getUser())
                .productInCarts(cart.getProductInCarts())
                .build();

        OrderCriteriaForUserDto orderCriteria = OrderCriteriaForUserDto.builder()
                .userId(cartExp.getUser().getId())
                .status(OrderStatus.PENDING)
                .build();

        OrderDetails orderDetails = OrderDetails.builder()
                .address("Belarus")
                .deliveryMethod(DeliveryMethod.DELIVERY)
                .paymentMethod(PaymentMethod.CARD)
                .build();

        orderService.createOrder(orderDetails);
        List<OrderDto> orderDtoList = orderService.showOrdersByFilter(orderCriteria);
        assertEquals(1, orderDtoList.size());
        assertEquals(cartExp.getTotalCost(), orderDtoList.get(0).getTotalCost());
        assertEquals(cartExp.getQuantityProduct(), orderDtoList.get(0).getQuantityProduct());
        assertEquals(String.valueOf(OrderStatus.PENDING), orderDtoList.get(0).getStatus());
    }

    @Test
    public void testCreateOrder(){
        Cart cart = cartService.getCart();
        Cart cartExp = Cart.builder()
                .quantityProduct(cart.getQuantityProduct())
                .totalCost(cart.getTotalCost())
                .user(cart.getUser())
                .productInCarts(cart.getProductInCarts())
                .build();
        OrderDetails orderDetails = OrderDetails.builder()
                .address("Belarus")
                .deliveryMethod(DeliveryMethod.DELIVERY)
                .paymentMethod(PaymentMethod.CARD)
                .build();

        OrderDto orderDto = orderService.createOrder(orderDetails);
        assertEquals(cartExp.getTotalCost(), orderDto.getTotalCost());
        assertEquals(2, orderDto.getProductInOrders().get(0).getProduct().getQuantity());
        assertEquals(cartExp.getQuantityProduct(), orderDto.getQuantityProduct());
        assertEquals(orderDto.getStatus(), String.valueOf(OrderStatus.PENDING));
    }

    @Test
    public void testCancelOrder(){
        Cart cart = cartService.getCart();
        Cart cartExp = Cart.builder()
                .quantityProduct(cart.getQuantityProduct())
                .totalCost(cart.getTotalCost())
                .user(cart.getUser())
                .productInCarts(cart.getProductInCarts())
                .build();
        OrderDetails orderDetails = OrderDetails.builder()
                .address("Belarus")
                .deliveryMethod(DeliveryMethod.DELIVERY)
                .paymentMethod(PaymentMethod.CARD)
                .build();

        OrderDto orderDto = orderService.createOrder(orderDetails);
        assertEquals(2, orderDto.getProductInOrders().get(0).getProduct().getQuantity());
        orderDto = orderService.cancelOrder(orderDto.getId());
        assertEquals(String.valueOf(OrderStatus.CANCELED), orderDto.getStatus());
        assertEquals(7, orderDto.getProductInOrders().get(0).getProduct().getQuantity());
    }

    @Test
    public void testCartToOrder(){
        Cart cart = cartService.getCart();
        Order orderExp = Order.builder()
                .quantityProduct(5)
                .totalCost(6000)
                .status(OrderStatus.PENDING)
                .build();
        Order order = orderService.cartToOrder(cart);

        assertEquals(orderExp.getQuantityProduct(), order.getQuantityProduct());
        assertEquals(orderExp.getTotalCost(), order.getTotalCost());
        assertEquals(orderExp.getStatus(), order.getStatus());
    }


}

package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.OrderDto;
import com.max.shop.entity.Cart;
import com.max.shop.entity.Order;
import com.max.shop.entity.OrderStatus;
import com.max.shop.entity.Product;
import com.max.shop.entity.ProductInCart;
import com.max.shop.entity.ProductInOrder;
import com.max.shop.entity.Status;
import com.max.shop.exception.CartIsEmptyException;
import com.max.shop.exception.ProductsNotEnoughException;
import com.max.shop.repository.OrderRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceForUser {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;
    private final MapperService conversionService;

    public List<OrderDto> showOrdersByUser() {
        List<Order> orders = orderRepository.findOrdersByUserId(SecurityUtil.getUserId());
        return conversionService.convertList(orders, OrderDto.class);
    }

    public OrderDto showOneOrderById(Long id) {
        Order order = orderRepository.findOrderById(id);
        return conversionService.convert(order, OrderDto.class);
    }

    @Transactional
//    TODO: It works, but i will optimize it
    public OrderDto createOrder() {

        Cart cart = cartService.returnCart();
        if (cart == null || cart.getProductInCarts().isEmpty()) {
            throw new CartIsEmptyException();
        }

        List<Long> productIdList = cart.getProductInCarts()
                .stream()
                .map(ProductInCart::getProductId).collect(Collectors.toList());

        List<Product> products = productService.findAllByIdInProductsId(productIdList);

        Map<Long, Integer> cartMap = new HashMap<>(cart.getProductInCarts().size());
        Map<Long, Integer> productMap = new HashMap<>(products.size());
        cart.getProductInCarts().forEach(item -> cartMap.put(item.getProductId(), item.getQuantity()));
        products.forEach(item -> productMap.put(item.getId(), item.getQuantity()));

        for (Map.Entry<Long, Integer> productEntry : productMap.entrySet()) {
            if (productEntry.getValue() >= cartMap.get(productEntry.getKey())) {
                Product product = productService.findObeById(productEntry.getKey());
                product.setQuantity(productEntry.getValue() - cartMap.get(productEntry.getKey()));
            } else {
                throw new ProductsNotEnoughException();
            }
        }
        List<ProductInOrder> productInOrderList = new ArrayList<>(cart.getProductInCarts().size());
        cart.getProductInCarts().forEach(item -> productInOrderList.add(ProductInOrder.builder()
                .cost(item.getCost())
                .name(item.getName())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .build()));


        Order order = Order.builder()
                .productInOrders(productInOrderList)
                .quantityProduct(cart.getQuantityProduct())
                .totalCost(cart.getTotalCost())
                .user(cart.getUser())
                .status(OrderStatus.ABANDONED)
                .build();

        orderRepository.save(order);
        cartService.cleanCart();
        return conversionService.convert(order, OrderDto.class);
    }
}

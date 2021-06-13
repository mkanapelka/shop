package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.OrderDto;
import com.max.shop.entity.Cart;
import com.max.shop.entity.Order;
import com.max.shop.entity.OrderStatus;
import com.max.shop.entity.Product;
import com.max.shop.entity.ProductInCart;
import com.max.shop.entity.ProductInOrder;
import com.max.shop.exception.CartIsEmptyException;
import com.max.shop.exception.OrderNotFoundException;
import com.max.shop.exception.ProductsNotEnoughException;
import com.max.shop.repository.OrderRepository;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceForUser {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductInOrderService productInOrderService;
    private final ProductService productService;
    private final MapperService conversionService;

    public List<OrderDto> showOrdersByUser() {
        List<Order> orders = orderRepository.findOrdersByUserId(SecurityUtil.getUserId());
        return conversionService.convertList(orders, OrderDto.class);
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

        Order order = Order.builder()
//                .productInOrders(productInOrderList)
                .quantityProduct(cart.getQuantityProduct())
                .totalCost(cart.getTotalCost())
                .user(cart.getUser())
                .status(OrderStatus.ABANDONED)
                .build();

        List<ProductInOrder> productInOrderList = new ArrayList<>(cart.getProductInCarts().size());

        for (ProductInCart productInCart : cart.getProductInCarts()) {
            ProductInOrder productInOrder = ProductInOrder.builder()
                    .cost(productInCart.getCost())
                    .name(productInCart.getName())
                    .productId(productInCart.getProductId())
                    .quantity(productInCart.getQuantity())
                    .order(order)
                    .build();
            productInOrderList.add(productInOrder);
            productInOrderService.save(productInOrder);
        }

        order.setProductInOrders(productInOrderList);
        orderRepository.save(order);
        cartService.cleanCart();
        return conversionService.convert(order, OrderDto.class);
    }


    @Transactional
    public void cancelOrder(Long id) {
        List<Order> orderList = orderRepository.findOrdersByUserId(SecurityUtil.getUserId());
        Order order = orderList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst().orElseThrow(OrderNotFoundException::new);

        List<ProductInOrder> productList = order.getProductInOrders();
        for (ProductInOrder productInOrder : productList) {
            Product product = productService.findObeById(productInOrder.getProductId());
            product.setQuantity(product.getQuantity() + productInOrder.getQuantity());
        }

        for (int i = 0; i < order.getProductInOrders().size(); i++) {
            order.getProductInOrders().remove(i);
        }
        order.setUser(null);
        orderRepository.save(order);
        orderRepository.deleteById(id);
    }
}

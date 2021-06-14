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
import com.max.shop.exception.EntityNotFountException;
import com.max.shop.exception.ProductsNotEnoughException;
import com.max.shop.exception.WrongOrderException;
import com.max.shop.repository.OrderRepository;
import com.max.shop.service.CartService;
import com.max.shop.service.ProductInOrderService;
import com.max.shop.service.ProductService;
import com.max.shop.specification.OrderSpecification;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceForUser {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductInOrderService productInOrderService;
    private final ProductService productService;
    private final MapperService conversionService;

//    public List<OrderDto> showOrdersByUser(OrderStatus status) {
//        List<Order> orders = orderRepository.findAll(OrderSpecification.buildListFilterForAdmin());
//        return conversionService.convertList(orders, OrderDto.class);
//    }
//
//    @Transactional
//    //    TODO: It works, but i will optimize it
//    public OrderDto createOrder() {
//
//        Cart cart = cartService.returnCart();
//        if (cart == null || cart.getProductInCarts().isEmpty()) {
//            throw new CartIsEmptyException();
//        }
//
//        List<Long> productIdList = cart.getProductInCarts()
//                .stream()
//                .map(ProductInCart::getProductId).collect(Collectors.toList());
//
//        List<Product> products = productService.findAllByIdInProductsId(productIdList);
//
//        cart.getProductInCarts()
//                .forEach(pic -> {
//                    val product = products.stream().filter(it -> Objects.equals(it.getId(), pic.getProductId()))
//                            .findFirst().orElseThrow(() -> new EntityNotFountException("product"));
//                    if (product.getQuantity() >= pic.getQuantity()) {
//                        product.setQuantity(product.getQuantity() - pic.getQuantity());
//                    } else {
//                        throw new ProductsNotEnoughException();
//                    }
//                });
//
//        Order order = Order.builder()
//                //                .productInOrders(productInOrderList)
//                .quantityProduct(cart.getQuantityProduct())
//                .totalCost(cart.getTotalCost())
//                .user(cart.getUser())
//                .status(OrderStatus.PENDING)
//                .build();
//
//        List<ProductInOrder> productInOrderList = cart.getProductInCarts().stream()
//                .map(productInCart -> ProductInOrder.builder()
//                        .cost(productInCart.getCost())
//                        .name(productInCart.getName())
//                        .productId(productInCart.getProductId())
//                        .quantity(productInCart.getQuantity())
//                        .order(order)
//                        .build())
//                .collect(Collectors.toList());
//
//        order.setProductInOrders(productInOrderList);
//        orderRepository.save(order);
//        cartService.cleanCart();
//        return conversionService.convert(order, OrderDto.class);
//    }
//
//
//    @Transactional
//    public void cancelOrder(Long id) {
//        Order order = orderRepository.findOrderById(id);
//        if (!Objects.equals(order.getUser().getId(), SecurityUtil.getUserId())) {
//            throw new WrongOrderException();
//        }
//
//        val productList = productService.findAllByIdInProductsId(order.getProductInOrders().stream()
//                .map(ProductInOrder::getProductId)
//                .collect(Collectors.toList()))
//                .forEach(product -> product.setQuantity(product.getQuantity() + productInOrder.getQuantity()));
//
//        //        for (ProductInOrder productInOrder : productList) {
//        //            Product product = productService.findObeById(productInOrder.getProductId());
//        //            product.setQuantity(product.getQuantity() + productInOrder.getQuantity());
//        //        }
//
//        order.setStatus(OrderStatus.CANCELED);
//        orderRepository.save(order);
//    }
}

package com.max.shop.service;

import com.max.shop.converter.MapperService;
import com.max.shop.dto.OrderDto;
import com.max.shop.dto.request.OrderCriteriaForUserDto;
import com.max.shop.entity.Cart;
import com.max.shop.entity.Order;
import com.max.shop.entity.OrderStatus;
import com.max.shop.entity.Product;
import com.max.shop.entity.ProductInCart;
import com.max.shop.entity.ProductInOrder;
import com.max.shop.entity.embeddable.OrderDetails;
import com.max.shop.exception.CartIsEmptyException;
import com.max.shop.exception.OrderNotFoundException;
import com.max.shop.exception.ProductsNotEnoughException;
import com.max.shop.exception.UserIsNotRegisteredException;
import com.max.shop.exception.WrongOrderException;
import com.max.shop.repository.OrderRepository;
import com.max.shop.specification.OrderSpecification;
import com.max.shop.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final MapperService conversionService;

    public List<OrderDto> showOrdersByUser(OrderCriteriaForUserDto criteriaDto) {
        List<Order> orders = orderRepository
                .findAll(OrderSpecification.buildListFilterForUser(criteriaDto).and(OrderSpecification.fetchProducts()));
        return conversionService.convertList(orders, OrderDto.class);
    }

    @Transactional
    public OrderDto createOrder(OrderDetails orderDetails) {

        if (!SecurityUtil.getUser().getIsActive()){
            throw new UserIsNotRegisteredException();
        }

        Cart cart = cartService.getCart();
        if (cart == null || cart.getProductInCarts().isEmpty()) {
            throw new CartIsEmptyException();
        }

        List<ProductInCart> productIdList = cart.getProductInCarts();
        for (ProductInCart productInCart : productIdList) {
            if (productInCart.getProduct().getQuantity() >= productInCart.getQuantity()) {
                Product product = productInCart.getProduct();
                product.setQuantity(product.getQuantity() - productInCart.getQuantity());
            } else {
                throw new ProductsNotEnoughException();
            }
        }

        Order order = cartToOrder(cart);
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        cartService.cleanCart();
        return conversionService.convert(order, OrderDto.class);
    }


    @Transactional
    public void cancelOrder(Long id) {
        Order order = orderRepository
                .findOne(OrderSpecification.findOrderById(id).and(OrderSpecification.fetchProducts()))
                .orElseThrow(OrderNotFoundException::new);
        if (!Objects.equals(order.getUser().getId(), SecurityUtil.getUserId())) {
            throw new WrongOrderException();
        }

        List<ProductInOrder> productInOrderList = order.getProductInOrders();
        for (ProductInOrder productInOrder : productInOrderList) {
            Product product = productInOrder.getProduct();
            product.setQuantity(product.getQuantity() + productInOrder.getQuantity());
        }

        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }

//    -------------------------------------------------------

    private Order cartToOrder(Cart cart) {
        Order order = new Order();
        List<ProductInOrder> productInOrderList = cart.getProductInCarts().stream()
                .map(productInCart -> ProductInOrder.builder()
                        .quantity(productInCart.getQuantity())
                        .product(productInCart.getProduct())
                        .order(order)
                        .build())
                .collect(Collectors.toList());

        order.setProductInOrders(productInOrderList);
        order.setQuantityProduct(cart.getQuantityProduct());
        order.setTotalCost(cart.getTotalCost());
        order.setUser(cart.getUser());
        order.setStatus(OrderStatus.PENDING);
        return order;
    }
}

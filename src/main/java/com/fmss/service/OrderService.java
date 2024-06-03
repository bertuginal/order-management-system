package com.fmss.service;

import com.fmss.model.Order;
import com.fmss.repository.repositories.OrderRepository;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class OrderService {
    private final OrderRepository orderRepository;
    private Long orderId = 0L;


    private static final Integer ORDER_CODE_LENGTH = 10;
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Set<String> generatedCodes = new HashSet<>();
    private static final Random random = new Random();

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        order.setId(++orderId);
        orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public void findAll() {
        orderRepository.findAll().forEach(System.out::println);
    }

    public Order findById(Long id) {
        return orderRepository.findAll().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public String generateOrderCode() {
        String orderCode;
        do {
            orderCode = generateRandomCode();
        } while (generatedCodes.contains(orderCode));
        generatedCodes.add(orderCode);
        return orderCode;
    }

    private String generateRandomCode() {
        StringBuilder sb = new StringBuilder(ORDER_CODE_LENGTH);
        for (int i = 0; i < ORDER_CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }
}

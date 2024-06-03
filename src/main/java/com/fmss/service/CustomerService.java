package com.fmss.service;

import com.fmss.model.Customer;
import com.fmss.model.Product;
import com.fmss.repository.repositories.CustomerRepository;

import java.time.Month;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    private final CustomerRepository customerRepository;
    private Long customerId = 0L;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        //this.initializeDataLoad();
    }

    public void save(Customer customer) {
        customer.setId(++customerId);
        customerRepository.save(customer);
    }

    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    public void findAll() {
        customerRepository.findAll().forEach(System.out::println);
    }

    public Customer findById(Long id) {
        return customerRepository.findAll().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void filterByFullNameContainingLetter(String letter) {
        Optional.ofNullable(customerRepository.findAll())
                .ifPresent(customers -> customers.stream()
                        .filter(customer -> customer.getFirstName().contains(letter) || customer.getLastName().contains(letter))
                        .forEach(System.out::println));
    }

    public void totalInvoiceAmountsForEnrolledInJune() {
        List<Double> totalInvoicesAmounts = customerRepository.findAll().stream()
                .filter(customer -> customer.getCreateDate().getMonth() == Month.JUNE)
                .map(this::calculateTotalInvoiceAmount)
                .toList();

        totalInvoicesAmounts.forEach(System.out::println);
    }

    public void filterNamesWithInvoicesUnderAmount(Double amount) {
        customerRepository.findAll().stream()
                .filter(customer -> customer.getOrderList().stream()
                        .anyMatch(order -> order.getInvoiceId()!= null &&
                                order.getProductList().stream()
                                        .map(Product::getPrice)
                                        .reduce(Double::sum)
                                        .orElse(0.0) < amount))
                .map(Customer::getFirstName)
                .toList()
                .forEach(System.out::println);
    }

    private double calculateTotalInvoiceAmount(Customer customer) {
        return customer.getOrderList().stream()
                .flatMap(order -> order.getProductList().stream())
                .map(Product::getPrice)
                .reduce(0.0, Double::sum);
    }

    /*private void initializeDataLoad() {
        List<Order> orderList1 = List.of(
                new Order(1L,1L, List.of(
                        new Product("Monster Abra A5", "Notebook", 2390.0, 100)))
        );

        List<Order> orderList2 = List.of(
                new Order(1L,1L, List.of(
                        new Product("Monster Abra A5", "Notebook", 2390.0, 100),
                        new Product("Samsung Galaxy A50", "Phone", 1230.0, 50)
                ))
        );

        List<Order> orderList3 = List.of(
                new Order(1L,1L, List.of(
                        new Product("Monster Abra A5", "Notebook", 2390.0, 100),
                        new Product("Samsung Galaxy A50", "Phone", 1230.0, 50),
                        new Product("Logitech G213", "Keyboard", 6200.0, 40)
                ))
        );

        this.save(new Customer("John", "Doe", "john.doe@example.com", "password123", orderList1));
        this.save(new Customer("Jane", "Doe", "jane.doe@example.com", "password456", orderList2));
        this.save(new Customer("Jim", "Brown", "jim.brown@example.com", "password789", orderList3));
    }*/
}

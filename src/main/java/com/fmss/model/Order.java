package com.fmss.model;

import java.util.List;

public class Order {
    private Long id;
    private String orderCode;
    private Long customerId;
    private Long invoiceId;
    private List<Product> productList;

    public Order(String orderCode, Long customerId, Long invoiceId, List<Product> productList) {
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.invoiceId = invoiceId;
        this.productList = productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderCode=" + orderCode +
                ", customerId=" + customerId +
                ", invoiceId=" + invoiceId +
                ", productList=" + productList +
                '}';
    }
}
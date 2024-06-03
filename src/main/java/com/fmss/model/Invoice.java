package com.fmss.model;

import java.time.LocalDateTime;

public class Invoice {
    private Long id;
    private LocalDateTime createdDatetime;
    private Double amount;

    public Invoice(LocalDateTime createdDatetime, Double amount) {
        this.createdDatetime = createdDatetime;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", createdDatetime=" + createdDatetime +
                ", amount=" + amount +
                '}';
    }
}
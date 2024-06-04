package com.fmss.model;

import java.time.LocalDateTime;

public class Invoice {
    private Long id;
    private LocalDateTime createdDate;
    private Double amount;

    public Invoice(LocalDateTime createdDate, Double amount) {
        this.createdDate = createdDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
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
                ", createdDate=" + createdDate +
                ", amount=" + amount +
                '}';
    }
}
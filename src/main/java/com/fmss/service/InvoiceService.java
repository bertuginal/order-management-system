package com.fmss.service;

import com.fmss.model.Invoice;
import com.fmss.repository.repositories.InvoiceRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private Long invoiceId = 0L;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public void save(Invoice invoice) {
        invoice.setId(++invoiceId);
        invoiceRepository.save(invoice);
    }

    public void delete(Invoice invoice) {
        invoiceRepository.delete(invoice);
    }

    public void findAll() {
        invoiceRepository.findAll().forEach(System.out::println);
    }

    public Invoice findById(Long id) {
        return invoiceRepository.findAll().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void filterHighValueInvoices(Double amount) {
        invoiceRepository.findAll().stream()
                .filter(invoice -> invoice.getAmount()!= null && invoice.getAmount() >= amount)
                .forEach(System.out::println);
    }

    public double calculateAverageOfHighValueInvoices() {
        List<Double> highValueInvoiceAmounts = invoiceRepository.findAll().stream()
                .filter(invoice -> invoice.getAmount()!= null && invoice.getAmount() >= 1500)
                .map(Invoice::getAmount)
                .toList();

        double totalAmount = highValueInvoiceAmounts.stream().mapToDouble(Double::doubleValue).sum();
        return totalAmount / highValueInvoiceAmounts.size();
    }



}

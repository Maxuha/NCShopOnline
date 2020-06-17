package ua.edu.sumdu.j2ee.zykov.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private boolean isProcessed;
    private LocalDateTime date;
    private Customer customer;

    public Order() {
    }

    public Order(boolean isProcessed, LocalDateTime date, Customer customer) {
        this.isProcessed = isProcessed;
        this.date = date;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

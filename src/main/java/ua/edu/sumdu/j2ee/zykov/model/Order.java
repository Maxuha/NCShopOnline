package ua.edu.sumdu.j2ee.zykov.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private boolean isProcessed;
    private LocalDateTime date;
    private User user;

    public Order() {
    }

    public Order(boolean isProcessed, LocalDateTime date, User user) {
        this.isProcessed = isProcessed;
        this.date = date;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

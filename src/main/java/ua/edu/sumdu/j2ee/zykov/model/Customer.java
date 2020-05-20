package ua.edu.sumdu.j2ee.zykov.model;

public class Customer {
    private int id;
    private String fullName;
    private User user;
    private Order order;

    public Customer() {
    }

    public Customer(int id, String fullName, User user, Order order) {
        this.id = id;
        this.fullName = fullName;
        this.user = user;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

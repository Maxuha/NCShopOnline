package ua.edu.sumdu.j2ee.zykov.model;

public class Shipper {
    private int id;
    private String title;
    private User user;
    private Order order;

    public Shipper() {
    }

    public Shipper(int id, String title, User user, Order order) {
        this.id = id;
        this.title = title;
        this.user = user;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

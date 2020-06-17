package ua.edu.sumdu.j2ee.zykov.model;

public class Customer {
    private User user;
    private String fullName;

    public Customer() {
    }

    public Customer(User user, String fullName) {
        this.user = user;
        this.fullName = fullName;
    }

    public Customer(String fullName) {
        this.fullName = fullName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

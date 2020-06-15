package ua.edu.sumdu.j2ee.zykov.model;

public class Customer {
    private int id;
    private String fullName;

    public Customer() {
    }

    public Customer(String fullName) {
        this.fullName = fullName;
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
}

package ua.edu.sumdu.j2ee.zykov.model;

import java.util.List;

public class Order {
    private int id;
    private int count;
    private List<Product> products;

    public Order() {
    }

    public Order(int id, int count, List<Product> products) {
        this.id = id;
        this.count = count;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

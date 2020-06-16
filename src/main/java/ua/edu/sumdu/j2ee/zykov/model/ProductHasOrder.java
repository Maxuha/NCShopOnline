package ua.edu.sumdu.j2ee.zykov.model;

public class ProductHasOrder {
    private Product product;
    private Order order;
    private int count;

    public ProductHasOrder() {
    }

    public ProductHasOrder(Product product, Order order, int count) {
        this.product = product;
        this.order = order;
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

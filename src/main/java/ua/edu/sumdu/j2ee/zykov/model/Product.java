package ua.edu.sumdu.j2ee.zykov.model;

public class Product {
    private int id;
    private String title;
    private String description;
    private float price;
    private float discount;
    private Category category;
    private Shipper shipper;

    public Product() {
    }

    public Product(int id, String title, String description, float price, float discount, Category category, Shipper shipper) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipper = shipper;
    }

    public Product(String title, String description, float price, float discount, Category category, Shipper shipper) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.category = category;
        this.shipper = shipper;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }
}

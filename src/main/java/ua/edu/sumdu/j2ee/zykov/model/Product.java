package ua.edu.sumdu.j2ee.zykov.model;

public class Product {
    private int id;
    private String title;
    private String description;
    private String pathToImage;
    private float price;
    private float discount;
    private Category category;

    public Product() {
    }

    public Product(int id, String title, String description, String pathToImage, float price, float discount, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.pathToImage = pathToImage;
        this.price = price;
        this.discount = discount;
        this.category = category;
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

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}

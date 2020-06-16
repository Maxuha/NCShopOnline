package ua.edu.sumdu.j2ee.zykov.model;

public class Category {
    private int id;
    private String title;
    private Image image;
    private Category parent;

    public Category() {
    }

    public Category(String title, Image image) {
        this.title = title;
        this.image = image;
    }

    public Category(String title, Image image, Category parent) {
        this.title = title;
        this.image = image;
        this.parent = parent;
    }

    public Category(int id, String title, Image image, Category parent) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.parent = parent;
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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}

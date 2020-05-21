package ua.edu.sumdu.j2ee.zykov.model;

public class Category {
    private int id;
    private String title;
    private String imageToPath;
    private Category parent;

    public Category() {
    }

    public Category(int id) {
        this.id = id;
    }

    public Category(int id, String title, String imageToPath, Category parent) {
        this.id = id;
        this.title = title;
        this.imageToPath = imageToPath;
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

    public String getImageToPath() {
        return imageToPath;
    }

    public void setImageToPath(String imageToPath) {
        this.imageToPath = imageToPath;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}

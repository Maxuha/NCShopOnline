package ua.edu.sumdu.j2ee.zykov.model;

public class Image {
    private int id;
    private String image;

    public Image() {
    }

    public Image(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

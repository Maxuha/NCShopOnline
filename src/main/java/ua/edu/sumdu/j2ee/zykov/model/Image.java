package ua.edu.sumdu.j2ee.zykov.model;

public class Image {
    private int id;
    private String pathToImage;

    public Image() {
    }

    public Image(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }
}

package ua.edu.sumdu.j2ee.zykov.model;

public class ImageToProduct {
    private Product product;
    private Image image;

    public ImageToProduct() {
    }

    public ImageToProduct(Product product, Image image) {
        this.product = product;
        this.image = image;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

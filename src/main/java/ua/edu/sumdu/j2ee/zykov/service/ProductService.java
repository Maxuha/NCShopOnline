package ua.edu.sumdu.j2ee.zykov.service;

import ua.edu.sumdu.j2ee.zykov.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductAll(int page, int size, String sortBy, String sortDir);
    List<Product> getProductByTitleOrShipper(String searchText);
    List<Product> getProductByCategoryId(int categoryId);
    Product getProductById(int id);
    Product addProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(Product product);
}

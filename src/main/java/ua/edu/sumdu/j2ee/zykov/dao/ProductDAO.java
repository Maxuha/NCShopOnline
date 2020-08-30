package ua.edu.sumdu.j2ee.zykov.dao;

import ua.edu.sumdu.j2ee.zykov.model.Product;
import ua.edu.sumdu.j2ee.zykov.model.ProductList;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll(String sortBy, String dirSort);
    List<Product> findByTitleOrShipper(String searchText);
    List<Product> findByCategoryId(int categoryId);
    Product findById(int id);
    Product save(Product product);
    Product update(Product product);
    Product delete(Product product);
    ProductList getCountForProducts();
}

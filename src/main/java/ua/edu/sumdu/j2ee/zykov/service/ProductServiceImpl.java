package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.ProductDAO;
import ua.edu.sumdu.j2ee.zykov.model.Product;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> getProductAll(String sortBy, String sortDir) {
        return productDAO.findAll(sortBy, sortDir);
    }

    @Override
    public List<Product> getProductByTitleOrShipper(String searchText) {
        return productDAO.findByTitleOrShipper(searchText);
    }

    @Override
    public List<Product> getProductByCategoryId(int categoryId) {
        return productDAO.findByCategoryId(categoryId);
    }

    @Override
    public Product getProductById(int id) {
        return productDAO.findById(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productDAO.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productDAO.update(product);
    }

    @Override
    public Product deleteProduct(Product product) {
        return productDAO.delete(product);
    }
}

package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.ProductDAO;
import ua.edu.sumdu.j2ee.zykov.model.Product;
import ua.edu.sumdu.j2ee.zykov.model.ProductList;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public List<Product> getProductAll(int page, int size, String sortBy, String sortDir) {
        int toId = (page + 1) * size;
        int fromId = toId - size + 1;
        return productDAO.findAll(fromId, toId, sortBy, sortDir);
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

    @Override
    public ProductList getCountForProduct(int size) {
        ProductList productList =  productDAO.getCountForProducts();
        int totalPages = productList.getTotalElements() / size;
        productList.setTotalPages(totalPages);
        return productList;
    }
}

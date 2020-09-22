package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.ProductDAO;
import ua.edu.sumdu.j2ee.zykov.exception.ProductNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.CategoryList;
import ua.edu.sumdu.j2ee.zykov.model.Product;
import ua.edu.sumdu.j2ee.zykov.model.ProductList;
import ua.edu.sumdu.j2ee.zykov.utils.CalculatePagination;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductList getProductAllPagination(int page, int size, String sortBy, String sortDir) {
        List<Product> products = productDAO.findAll(sortBy, sortDir);
        ProductList productList = getProductList(size);
        productList.setProducts(getProductsFromRange(products, page, size));
        productList.setNumber(page);
        return productList;
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
    public Product getProductById(int id) throws ProductNotExistException {
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

    private List<Product> getProductsFromRange(List<Product> products, int page, int size) {
        int from = page * size;
        int to = from + size;
        to = Math.min(to, products.size());
        return products.subList(from, to);
    }

    private ProductList getProductList(int size) {
        ProductList productList = productDAO.getProductListWithTotalElements();
        int totalPages = CalculatePagination.calculateTotalPagesForEntityList(productList, size);
        productList.setTotalPages(totalPages);
        return productList;
    }
}

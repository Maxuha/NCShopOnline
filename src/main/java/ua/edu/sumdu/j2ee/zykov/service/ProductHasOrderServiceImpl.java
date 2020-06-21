package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.ProductHasOrderDAO;
import ua.edu.sumdu.j2ee.zykov.model.ProductHasOrder;

import java.util.List;

@Service
public class ProductHasOrderServiceImpl implements ProductHasOrderService {
    private final ProductHasOrderDAO productHasOrderDAO;

    public ProductHasOrderServiceImpl(ProductHasOrderDAO productHasOrderDAO) {
        this.productHasOrderDAO = productHasOrderDAO;
    }

    @Override
    public List<ProductHasOrder> getProductHasOrderAll() {
        return productHasOrderDAO.findAll();
    }

    @Override
    public List<ProductHasOrder> getProductHasOrderByProduct(int id) {
        return productHasOrderDAO.findByProduct(id);
    }

    @Override
    public List<ProductHasOrder> getProductHasOrderByOrder(int id) {
        return productHasOrderDAO.findByOrder(id);
    }

    @Override
    public ProductHasOrder addProductHasOrder(ProductHasOrder productHasOrder) {
        return productHasOrderDAO.save(productHasOrder);
    }

    @Override
    public ProductHasOrder updateProductHasOrder(ProductHasOrder productHasOrder) {
        return productHasOrderDAO.update(productHasOrder);
    }

    @Override
    public ProductHasOrder deleteProductHasOrder(ProductHasOrder productHasOrder) {
        return productHasOrderDAO.delete(productHasOrder);
    }
}

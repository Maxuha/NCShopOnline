package ua.edu.sumdu.j2ee.zykov.dao;

import ua.edu.sumdu.j2ee.zykov.model.ProductHasOrder;

import java.util.List;

public interface ProductHasOrderDAO {
    List<ProductHasOrder> findAll();
    List<ProductHasOrder> findByProduct(int id);
    ProductHasOrder findByOrder(int id);
    ProductHasOrder save(ProductHasOrder productHasOrder);
    ProductHasOrder update(ProductHasOrder productHasOrder);
    ProductHasOrder delete(ProductHasOrder productHasOrder);
}

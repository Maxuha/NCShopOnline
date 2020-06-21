package ua.edu.sumdu.j2ee.zykov.service;

import ua.edu.sumdu.j2ee.zykov.model.ProductHasOrder;

import java.util.List;

public interface ProductHasOrderService {
    List<ProductHasOrder> getProductHasOrderAll();
    List<ProductHasOrder> getProductHasOrderByProduct(int id);
    List<ProductHasOrder> getProductHasOrderByOrder(int id);
    ProductHasOrder addProductHasOrder(ProductHasOrder productHasOrder);
    ProductHasOrder updateProductHasOrder(ProductHasOrder productHasOrder);
    ProductHasOrder deleteProductHasOrder(ProductHasOrder productHasOrder);
}

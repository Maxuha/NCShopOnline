package ua.edu.sumdu.j2ee.zykov.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductList {
    private List<Product> products;
    private Integer totalPages;
    private Integer totalElements;
    private Integer number;
}

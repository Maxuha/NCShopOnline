package ua.edu.sumdu.j2ee.zykov.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductHasOrder {
    private Product product;
    private Order order;
    private int count;
}

package ua.edu.sumdu.j2ee.zykov.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    private int id;
    @NonNull
    private String title;
    @NonNull
    private String description;
    @NonNull
    private float price;
    @NonNull
    private float discount;
    @NonNull
    private Category category;
    @NonNull
    private Shipper shipper;
}

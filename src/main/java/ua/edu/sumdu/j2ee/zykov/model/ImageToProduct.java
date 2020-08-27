package ua.edu.sumdu.j2ee.zykov.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class ImageToProduct {
    @NonNull
    private Product product;
    @NonNull
    private Image image;
}

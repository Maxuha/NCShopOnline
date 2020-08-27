package ua.edu.sumdu.j2ee.zykov.model;

import com.sun.istack.internal.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Category {
    private int id;
    @NotNull
    private String title;
    @NotNull
    private Image image;
    private Category parent;
}

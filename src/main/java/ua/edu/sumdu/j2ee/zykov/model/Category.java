package ua.edu.sumdu.j2ee.zykov.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private int id;
    private String title;
    private Image image;
    private Category parent;
}

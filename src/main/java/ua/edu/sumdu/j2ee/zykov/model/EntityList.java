package ua.edu.sumdu.j2ee.zykov.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class EntityList {
    protected Integer totalPages;
    protected Integer totalElements;
    protected Integer number;
}

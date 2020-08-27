package ua.edu.sumdu.j2ee.zykov.model;

import com.sun.istack.internal.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {
    @NotNull
    private User user;
    @NotNull
    private String fullName;
}

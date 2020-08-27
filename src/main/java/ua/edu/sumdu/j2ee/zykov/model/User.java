package ua.edu.sumdu.j2ee.zykov.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    @NonNull
    private String userName;
    @NonNull
    private String password;
}

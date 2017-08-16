package org.yava.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    public User( long id ) {
        this.id = id;
    }
}

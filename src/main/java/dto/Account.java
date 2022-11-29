package dto;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.Serializable;
@Data
@AllArgsConstructor
public class Account implements Serializable {
    private final String name;
    private String password;
    private Role role;

}

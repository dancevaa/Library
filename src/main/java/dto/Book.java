package dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Book implements Serializable {
    private String name;
    private String content;

}

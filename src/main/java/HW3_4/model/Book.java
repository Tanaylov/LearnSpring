package HW3_4.model;

import lombok.*;

//@Data
//@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Book {
    private static long idCounter = 1L;
    private final long ID;
    private final String NAME;
    private int copyQuantity;

    {ID = idCounter++;}
    public Book(){NAME = "new book";}
    public Book(String name) {this(name, 1);}
    public Book(String name, int copyQuantity) {
        this.NAME = name;
        if (copyQuantity < 1) copyQuantity = 1;
        this.copyQuantity = copyQuantity;
    }

}

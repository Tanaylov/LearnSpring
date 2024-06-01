package HW3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reader {
    private static long idCounter = 1L;
    private final long ID;
    private final String NAME;
    private boolean onHand;

    public Reader(String name) {this(idCounter++, name, false);}



}

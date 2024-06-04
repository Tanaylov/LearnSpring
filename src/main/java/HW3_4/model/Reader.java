package HW3_4.model;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Reader {
    private static long idCounter = 1L;
    private final long ID;
    private final String NAME;
    private boolean onHand = false;
    private Book bookOnHand = null;

    {ID=idCounter++;}
    public Reader(String name) {
        this.NAME = name;
    }

    public long getBookId() {
        if (!onHand) return 0;
        return bookOnHand.getID();
    }


}

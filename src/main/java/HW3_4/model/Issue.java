package HW3_4.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Issue {
    private static long idCounter = 1L;
    private final long ID;
    private final long READER_ID;
    private final long BOOK_ID;
    private final LocalDateTime ISSUE_AT;
    private LocalDateTime returnAt;

    public Issue(long READER_ID, long BOOK_ID) {
        ID = idCounter++;
        this.READER_ID = READER_ID;
        this.BOOK_ID = BOOK_ID;
        ISSUE_AT = LocalDateTime.now();
    }

}

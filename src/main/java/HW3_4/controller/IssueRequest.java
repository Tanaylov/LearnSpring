package HW3_4.controller;

import lombok.Data;

@Data
public class IssueRequest {
    private final long readerID;
    private final long bookID;
}

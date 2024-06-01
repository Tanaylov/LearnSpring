package HW3.controller;

import lombok.Data;

@Data
public class IssueRequest {
    private final long readerID;
    private final long bookID;
}

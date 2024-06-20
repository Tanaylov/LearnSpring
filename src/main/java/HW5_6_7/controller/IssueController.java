package HW5_6_7.controller;

import HW5_6_7.model.Issue;
import HW5_6_7.service.IssueService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/issue")
@Tag(name = "Issue", description = "Operations of issuing books and receiving them from readers")
public class IssueController {

    private final IssueService issueService;
    @PostMapping
    public ResponseEntity<?> addNewIssue(@RequestBody Issue newIssue) {
        final Issue issue;

        try {
            issue = issueService.addNewIssue(newIssue);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok(issue);
    }

    @PutMapping(path = "/close")
    public ResponseEntity<?> closeIssueById(@RequestParam int id) {
        if (!issueService.closeIssueById(id))
            return ResponseEntity.status(404).body("There is no issue with such id# " + id);
        return ResponseEntity.ok().build();
    }

}

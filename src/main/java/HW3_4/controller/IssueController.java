package HW3_4.controller;

import HW3_4.ZeroCopyOfBookException;
import HW3_4.model.Issue;
import HW3_4.repository.IssueRepository;
import HW3_4.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
@Slf4j
@RestController
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;
    @Autowired
    private IssueRepository issueRepository;
    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
        final Issue issue;
        try {
            issue = issueService.issue(request);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (ZeroCopyOfBookException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.LOCKED).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(issue);
    }

    @PutMapping(path = "/close/{id}")
    public ResponseEntity<Issue> issueClose(@PathVariable long id) {
        final Issue closingIssue;
        try {
            closingIssue = issueService.issueClose(id);
        } catch (NoSuchElementException e) {
            log.info(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            log.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(closingIssue);
    }
    @GetMapping(path = "/all")
    public List<Issue> getAll() {return issueRepository.getAll();}
    @GetMapping(path = "/all/book_id")
    public List<Issue> getAllByBookId(@RequestParam(name = "id") long id) {return issueRepository.getIssueByBookId(id);}
    @GetMapping(path = "/all/reader_id")
    public List<Issue> getAllByReaderId(@RequestParam(name = "id") long id) {return issueRepository.getIssueByReaderId(id);}

}

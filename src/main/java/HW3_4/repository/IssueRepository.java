package HW3_4.repository;

import HW3_4.model.Issue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {

    private final List<Issue> issues;
    public IssueRepository() {issues = new ArrayList<>(100);}

    public void saveIssue(Issue newIssue) {issues.add(newIssue);}

    public List<Issue> getAll() {return List.copyOf(issues);}
    public List<Issue> getIssueByBookId(long id) {
        return issues.stream()
                .filter(el -> Objects.equals(el.getBOOK_ID(), id))
                .toList();
    }
    public List<Issue> getIssueByReaderId(long id) {
        return issues.stream()
                .filter(el -> Objects.equals(el.getREADER_ID(), id))
                .toList();
    }

    public Issue getIssueById(long id) {
        return issues.stream()
                .filter(el -> Objects.equals(el.getID(), id))
                .findFirst()
                .orElse(null);
    }
}

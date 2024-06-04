package HW3_4.service;

import HW3_4.model.Book;
import HW3_4.model.Issue;
import HW3_4.repository.BookRepository;
import HW3_4.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;

    public List<Book> getAllReadingBook(long readerId) {
        List<Issue> readerIssues = issueRepository.getAll().stream()
                .filter(el -> Objects.equals(el.getREADER_ID(), readerId))
                .filter(el -> el.getReturnAt() != null)
                .toList();
        List<Book> readerBooks = new ArrayList<>();
        for (Issue issue : readerIssues) {
            readerBooks.add(bookRepository.getBookById(issue.getBOOK_ID()));
        }
        return readerBooks;
    }
}

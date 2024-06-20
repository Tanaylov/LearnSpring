package HW5_6_7.service;

import HW5_6_7.model.Book;
import HW5_6_7.model.Issue;
import HW5_6_7.model.Reader;
import HW5_6_7.repository.IssueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IssueService {

    private final IssueRepository issueRepository;
    private final ReaderService readerService;
    private final BookService bookService;

    public Issue addNewIssue(Issue newIssue) {
        Optional<Reader> readerFromNewIssue = readerService.getReaderById(newIssue.getReaderId());
        Optional<Book> bookFromNewIssue = bookService.getBookById(newIssue.getBookId());

        Long readerId = newIssue.getReaderId();
        Long bookId = newIssue.getBookId();

        if (readerFromNewIssue.isEmpty())
            throw new NoSuchElementException("There is no reader with such id " + readerId);
        if (bookFromNewIssue.isEmpty())
            throw new NoSuchElementException("There is no book with such id " + bookId);

        bookFromNewIssue.get().setCopyQuantity(bookFromNewIssue.get().getCopyQuantity() - 1);
        readerFromNewIssue.get().setOnHand(true);
        return issueRepository.save(newIssue);
    }

    public boolean closeIssueById(long id) {
        Optional<Issue> closingIssue = issueRepository.findById(id);

        if (closingIssue.isEmpty())
            return false;

        readerService.getReaderById(closingIssue.get().getReaderId()).get().setOnHand(false);

        int copyQuantity = bookService.getCopyQuantityByBookId(closingIssue.get().getBookId());
        bookService.getBookById(closingIssue.get().getBookId()).get()
                .setCopyQuantity(copyQuantity + 1);

        issueRepository.setReturnAtByIssueId(LocalDateTime.now(), id);

        return true;
    }


    public List<Issue> getAll() {
        return issueRepository.findAll();
    }
}

package HW3.service;

import HW3.ZeroCopyOfBookException;
import HW3.controller.IssueRequest;
import HW3.model.Book;
import HW3.model.Issue;
import HW3.model.Reader;
import HW3.repository.BookRepository;
import HW3.repository.IssueRepository;
import HW3.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;
    private final BookRepository bookRepository;

    public Issue issue(IssueRequest request) throws ZeroCopyOfBookException {
        long bookIDFromRequest = request.getBookID();
        long readerIDFromRequest = request.getReaderID();

        Reader readerFromRequest = readerRepository.getReaderById(readerIDFromRequest);
        Book bookFromRequest = bookRepository.getBookById(bookIDFromRequest);

        if (bookFromRequest == null) {
            throw new NoSuchElementException(String.format("There is no book with such ID: %d", bookIDFromRequest));
        } else if (bookFromRequest.getCopyQuantity() == 0) {
            throw new ZeroCopyOfBookException(String.format("There isn't any copy of this book (id: %d)", bookIDFromRequest));
        }
        if (readerFromRequest == null) {
            throw new NoSuchElementException(String.format("There is no reader with such ID: %s", readerIDFromRequest));
        } else if (readerFromRequest.isOnHand()) {
            throw new IllegalArgumentException(String.format("The reader (ID: %s) already has the book", readerIDFromRequest));
        }

        Issue newIssue = new Issue(readerIDFromRequest, bookIDFromRequest);
        issueRepository.saveIssue(newIssue);
        readerFromRequest.setOnHand(true);
        bookFromRequest.setCopyQuantity(bookFromRequest.getCopyQuantity() - 1);
        return  newIssue;
    }

    public Issue issueClose(long id) {
        Issue closingIssue = issueRepository.getIssueById(id);
        if (closingIssue == null)
            throw new NoSuchElementException(String.format("There is no issue with id: %d", id));
        else if (closingIssue.getReturnAt() != null)
            throw new IllegalArgumentException(String.format("Issue with id: %d already closed", id));
        closingIssue.setReturnAt(LocalDateTime.now());
        readerRepository.getReaderById(closingIssue.getREADER_ID()).setOnHand(false);
        Book bookById = bookRepository.getBookById(closingIssue.getBOOK_ID());
        bookById.setCopyQuantity(bookById.getCopyQuantity() + 1);
        return closingIssue;
    }

}

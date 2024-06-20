package HW5_6_7.service;

import HW5_6_7.model.Book;
import HW5_6_7.model.Reader;
import HW5_6_7.model.Issue;
import HW5_6_7.repository.IssueRepository;
import HW5_6_7.repository.ReaderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReaderService {

    private ReaderRepository readerRepository;
    private IssueRepository issueRepository;
    private BookService bookService;

    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }
    public Optional<Reader> getReaderById(long id) {
        return readerRepository.findById(id);
    }
    public List<Reader> getReaderByName(String name) {
        return readerRepository.findByName(name);
    }
    public List<Book> getAllReadingBook(long readerId) {
        List<Issue> readerIssues = issueRepository.findAll().stream()
                .filter(el -> Objects.equals(el.getReaderId(), readerId))
                .filter(el -> el.getReturnAt() != null)
                .toList();
        List<Book> readerBooks = new ArrayList<>();
        for (Issue issue : readerIssues) {
            readerBooks.add(bookService.getBookById(issue.getBookId()).get());
        }
        return readerBooks;
    }

    public Reader addNewReader(Reader newReader) {
        return readerRepository.save(newReader);
    }

    public boolean deleteById(long id) {
        if (readerRepository.findById(id).isEmpty())
            return false;
        readerRepository.deleteById(id);
        return true;
    }
}

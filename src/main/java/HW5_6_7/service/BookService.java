package HW5_6_7.service;

import HW5_6_7.model.Book;
import HW5_6_7.properties.BookProperties;
import HW5_6_7.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(BookProperties.class)
public class BookService {

    private final BookRepository bookRepository;
    private final BookProperties bookProperties;

    public Book addNewBook (Book newBook) {
        if (newBook.getCopyQuantity() == null)
            newBook.setCopyQuantity(bookProperties.getCopyQuantity());

        return bookRepository.save(newBook);
    }
    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }
    public Optional<Book> getBookByName(String name) {
        return bookRepository.findByTitle(name);
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }
    public boolean deleteBookById(long id) {
        if (bookRepository.findById(id).isEmpty())
            return false;
        bookRepository.deleteById(id);
        return true;
    }
    public void deleteBookByIds(List<Long> idList) {
        bookRepository.deleteAllById(idList);
    }

    public int getCopyQuantityByBookId(long id) {
        return bookRepository.findCopyQuantityByBookId(id);
    }
}

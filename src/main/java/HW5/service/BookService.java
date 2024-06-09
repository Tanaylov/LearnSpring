package HW5.service;

import HW5.model.Book;
import HW5.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book addNewBook (Book newBook) {
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

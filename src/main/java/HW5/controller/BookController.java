package HW5.controller;

import HW5.model.Book;
import HW5.repository.BookRepository;
import HW5.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/book")
public class BookController {

    private final BookService bookService;

    @GetMapping(path = "/id")
    public ResponseEntity<Book> getBookById(@RequestParam(name = "id") long bookId) {
        if (bookService.getBookById(bookId).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(bookId).get());
    }

    @GetMapping(path = "/title")
    public ResponseEntity<Book> getBookByName(@RequestParam(name = "name") String bookName) {
        if (bookService.getBookByName(bookName).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookByName(bookName).get());
    }
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book addNewBook(@RequestBody Book newBook) {
        return bookService.addNewBook(newBook);
    }

    @DeleteMapping(path = "/delete/all")
    public void deleteAllBooks() {
        bookService.deleteAllBooks();
    }
    @DeleteMapping(path = "/delete/id")
    public ResponseEntity<Void> deleteBookById(@RequestParam(name = "id") long bookId) {
        if (bookService.deleteBookById(bookId))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping(path = "delete/ids")
    public void deleteBooksByIds(@RequestParam(name = "ids") List<Long> idList) {
        bookService.deleteBookByIds(idList);
    }
}

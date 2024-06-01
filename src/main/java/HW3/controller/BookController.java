package HW3.controller;

import HW3.model.Book;
import HW3.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookRepository bookRepository;
    @Autowired
    public BookController(BookRepository bookRepository) {this.bookRepository = bookRepository;}

    //region GET_MAPPING
    @GetMapping("/all")
    public List<Book> getAllBooks() {return bookRepository.getALL();}
    @GetMapping(path = "/id")
    public ResponseEntity<Book> getBookById(@RequestParam long id) {
        final Book bookById = bookRepository.getBookById(id);
        if (bookById == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(bookById);
    }
    @GetMapping(path = "/name")
    public ResponseEntity<Book> getBootByName(@RequestParam String name) {
        final Book bookByName = bookRepository.getBookByName(name);
        if (bookByName == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(bookByName);
    }
    //endregion

    @DeleteMapping(path = "/delete/id/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable(name = "id") long bookId) {
        Book deletingBook = bookRepository.deleteBookById(bookId);
        if (deletingBook == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(deletingBook);
    }

    @PostMapping(path = "/new")
    public ResponseEntity<String> addBookByName(@RequestBody Book newBook) {
        if (bookRepository.addNewBook(newBook)) return ResponseEntity.status(HttpStatus.CREATED).body("Book added");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Book already exists");
    }

}

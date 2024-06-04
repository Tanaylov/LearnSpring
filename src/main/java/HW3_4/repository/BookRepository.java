package HW3_4.repository;

import HW3_4.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository {
    private final List<Book> books;
    @Value("${book.copy}")
    private int numberOfCopies;

    public BookRepository() {
        this.books = new ArrayList<>(100);
    }

    @PostConstruct
    public void fillBooks() {
        books.addAll(List.of(
            new Book("War and Peace", numberOfCopies),
            new Book("Clean code", 4),
            new Book("Chicken soup for the soul", 1),
            new Book("Metro 2033", 5),
            new Book("Dead souls", 3)));
    }

    //region GET:
    public List<Book> getALL() {return List.copyOf(books);}
    public Book getBookById(long id) {
        return books.stream()
                .filter(el -> Objects.equals(el.getID(), id))
                .findFirst()
                .orElse(null);
    }
    public Book getBookByName(String name) {
        return books.stream()
                .filter(el -> Objects.equals(el.getNAME().toLowerCase(), name.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    //endregion
    public Book deleteBookById(long id) {
        Book deletingBook = getBookById(id);
        if (deletingBook == null) return null;
        books.remove(deletingBook);
        return deletingBook;
    }
    public boolean addNewBook(Book newBook) {
        Book bookInRepository = getBookByName(newBook.getNAME());
        if (bookInRepository == null) {
            books.add(new Book(newBook.getNAME(), newBook.getCopyQuantity()));
            return true;
        }
        bookInRepository.setCopyQuantity(bookInRepository.getCopyQuantity() + newBook.getCopyQuantity());
        return false;
    }

}

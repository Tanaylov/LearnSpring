package HW5_6_7.controller;

import HW5_6_7.model.Book;
import HW5_6_7.repository.BookRepository;
import HW5_6_7.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class BookControllerTest extends TestSpringBootBase {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService;
    @Autowired
    WebTestClient webTestClient;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("GET book by ID")
    void getBookByIdSuccess() {
        Long maxId = jdbcTemplate.queryForObject("select max(id) from book", Long.class);
        Book expectedBook = bookRepository.findById(maxId).get();

        Book responseBook = webTestClient.get()
                .uri("/book/id?id=" + maxId)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult().getResponseBody();

        assertNotNull(responseBook);
        assertEquals(expectedBook.getId(), responseBook.getId());
        assertEquals(expectedBook.getTitle(), responseBook.getTitle());
    }

    @Test
    @DisplayName("GET book by non-existing ID")
    void getBookByIdNotFound() {
        Long nonExistingId = jdbcTemplate.queryForObject("select max(id) from book", Long.class) + 1;

        webTestClient.get()
                .uri("/book/id?id=" + nonExistingId)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("GET book by title")
    void getBookByNameSuccess() {
        Long maxId = jdbcTemplate.queryForObject("select max(id) from book", Long.class);
        String title = bookRepository.findById(maxId).get().getTitle();
        Book expectedBook = bookRepository.findByTitle(title).get();

        Book responceBook = webTestClient.get()
                .uri("/book/title?name=" + title)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult().getResponseBody();

        assertNotNull(responceBook);
        assertEquals(expectedBook.getTitle(), responceBook.getTitle());
        assertEquals(expectedBook.getId(), responceBook.getId());
    }

    @Test
    @DisplayName("GET book by non-existing title")
    void getBookByNameNotFound() {
        webTestClient.get()
                .uri("/book/title?name=unexpected Book")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("GET all books")
    void getAllBooks() {
        List<Book> expectedBooks = bookRepository.findAll();

        List<Book> responseBooks = webTestClient.get()
                .uri("/book")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Book>>() {
                })
                .returnResult().getResponseBody();

        int bookIndex = ThreadLocalRandom.current().nextInt(expectedBooks.size());

        assertEquals(expectedBooks.size(), responseBooks.size());
        assertNotNull(responseBooks);
        assertIterableEquals(expectedBooks, responseBooks);
        assertEquals(expectedBooks.get(bookIndex).getTitle(), responseBooks.get(bookIndex).getTitle());
    }

    @Test
    @DisplayName("Checking for add new book")
    void addNewBook() {
        Book bookWithQuantity = new Book();
        bookWithQuantity.setTitle("Book With Quantity");
        bookWithQuantity.setCopyQuantity(2);
        Book bookWithoutQuantity = new Book();
        bookWithoutQuantity.setTitle("Book Without Quantity");

        Book bookWithQuantityResponse = webTestClient.post()
                .uri("/book")
                .body(Mono.just(bookWithQuantity), Book.class)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(Book.class)
                .getResponseBody().blockFirst();

        Book bookWithoutQuantityResponse = webTestClient.post()
                .uri("/book")
                .body(Mono.just(bookWithoutQuantity), Book.class)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .returnResult(Book.class)
                .getResponseBody().blockFirst();

        assertNotNull(bookWithQuantityResponse);
        assertEquals(bookWithQuantity.getTitle(), bookWithQuantityResponse.getTitle());
        assertEquals(bookWithQuantity.getCopyQuantity(), bookWithQuantityResponse.getCopyQuantity());

        assertNotNull(bookWithoutQuantityResponse);
        assertEquals(bookWithoutQuantity.getTitle(), bookWithoutQuantityResponse.getTitle());
        assertEquals(1, bookWithoutQuantityResponse.getCopyQuantity());

    }

    @Test
    void deleteAllBooks() {
        List<Book> books = bookRepository.findAll();

        webTestClient.delete()
                .uri("/book/delete/all")
                .exchange()
                .expectStatus().isOk();

        bookRepository.saveAll(books);
    }

    @Test
    void deleteBookById() {
        List<Long> ids = jdbcTemplate.queryForList("select id from book", Long.class);
        System.out.println(ids);
        long id = Math.toIntExact(ids.get(ThreadLocalRandom.current().nextInt(ids.size())));
        Book book = bookRepository.findById(id).get();

        webTestClient.delete()
                .uri("/book/delete/id?id=" + id)
                .exchange()
                .expectStatus().isOk();

        bookRepository.save(book);
    }

    @Test
    void deleteBooksByIds() {
        List<Long> ids = jdbcTemplate.queryForList("select id from book", Long.class);
        List<Long> idsToDelete = ids.stream()
                .limit(ThreadLocalRandom.current().nextInt(1, ids.size()))
                .toList();
        StringBuilder idsToDeleteStr = new StringBuilder();
        for (Long id : idsToDelete) {
            idsToDeleteStr.append(id + ",");
        }
        idsToDeleteStr.deleteCharAt(idsToDeleteStr.length() - 1);

        List<Book> books = bookRepository.findAllById(idsToDelete);

        webTestClient.delete()
                .uri("/book/delete/ids?ids=" + idsToDeleteStr)
                .exchange()
                .expectStatus().isOk();

        bookRepository.saveAll(books);
    }
}
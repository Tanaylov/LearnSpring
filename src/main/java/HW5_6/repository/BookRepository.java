package HW5_6.repository;

import HW5_6.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    @Query(nativeQuery = true, value = "SELECT copy_quantity FROM book where id=?")
    int findCopyQuantityByBookId(@Param(value = "id") long bookId);
}

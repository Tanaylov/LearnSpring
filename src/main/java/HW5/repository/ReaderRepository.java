package HW5.repository;

import HW5.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

    List<Reader> findByName(String name);
}

package HW5_6_7.repository;

import HW5_6_7.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Long> {

    List<Reader> findByName(String name);
}

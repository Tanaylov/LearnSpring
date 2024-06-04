package HW3_4.repository;

import HW3_4.model.Reader;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

    private final List<Reader> readers;

    public ReaderRepository(IssueRepository issueRepository) {this.readers = new ArrayList<>(100);}

    @PostConstruct
    public void fillReaders() {
        readers.add(new Reader("Ivan"));
        readers.add(new Reader("Kirill"));
        readers.add(new Reader("Elena"));
        readers.add(new Reader("Anna"));
        readers.add(new Reader("Mark"));
    }

    //region GET:
    public List<Reader> getAll() {
        return List.copyOf(readers);
    }

    public Reader getReaderById(long id) {
        return readers.stream()
                .filter(el -> Objects.equals(el.getID(), id))
                .findFirst()
                .orElse(null);
    }
    public Reader getReaderByName(String name) {
        return readers.stream()
                .filter(el -> Objects.equals(el.getNAME().toLowerCase(), name.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
    //endregion

    public Reader addNew(Reader newReader) {
        Reader newReaderInRep = new Reader(newReader.getNAME());
        readers.add(newReaderInRep);
        return newReaderInRep;
    }
    public boolean deleteReader(Reader deletingReader) {
        Reader readerInRepository = getReaderById(deletingReader.getID());
        if (readerInRepository == null) return false;
        return readerInRepository.getNAME().equalsIgnoreCase(deletingReader.getNAME())
                ? readers.remove(readerInRepository) : false;
    }
}

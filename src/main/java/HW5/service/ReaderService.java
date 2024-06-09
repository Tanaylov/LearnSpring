package HW5.service;

import HW5.model.Reader;
import HW5.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }
    public Optional<Reader> getReaderById(long id) {
        return readerRepository.findById(id);
    }
    public List<Reader> getReaderByName(String name) {
        return readerRepository.findByName(name);
    }

    public Reader addNewReader(Reader newReader) {
        return readerRepository.save(newReader);
    }

    public boolean deleteById(long id) {
        if (readerRepository.findById(id).isEmpty())
            return false;
        readerRepository.deleteById(id);
        return true;
    }
}

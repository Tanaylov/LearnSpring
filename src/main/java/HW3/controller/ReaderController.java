package HW3.controller;

import HW3.model.Reader;
import HW3.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reader")
public class ReaderController {

    @Autowired
    private ReaderRepository readerRepository;

    @GetMapping(path = "/all")
    public List<Reader> getAll() {return readerRepository.getAll();}
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable(name = "id") long readerId) {
        Reader readerById = readerRepository.getReaderById(readerId);
        if (readerById == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(readerById);
    }
    @GetMapping(path = "/name/{name}")
    public ResponseEntity<Reader> getReaderByName(@PathVariable String name) {
        Reader readerByName = readerRepository.getReaderByName(name);
        if (readerByName == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.status(HttpStatus.OK).body(readerByName);
    }
    @GetMapping(path = "/id_name/{id}_{name}")
    public ResponseEntity<Reader> getReaderByIdAndName(@PathVariable(name = "id") long id,
                                                       @PathVariable(name = "name") String name) {
        if (readerRepository.getReaderById(id).equals(readerRepository.getReaderByName(name)))
            return ResponseEntity.status(HttpStatus.OK).body(readerRepository.getReaderById(id));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping(path = "/new")
    public Reader addNewReader(@RequestBody Reader newReader) {return readerRepository.addNew(newReader);}

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteReader (@RequestBody Reader deletingReader) {
        if (readerRepository.deleteReader(deletingReader))
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Reader was deleted");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no reader with such data");

    }

}

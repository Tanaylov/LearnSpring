package HW5.controller;

import HW5.model.Reader;
import HW5.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/reader")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable long id) {
        Optional<Reader> readerById = readerService.getReaderById(id);
        return readerById.map(reader -> ResponseEntity.status(HttpStatus.OK).body(reader))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping(path = "/name/{name}")
    public List<Reader> getReaderByName(@PathVariable String name) {
        return readerService.getReaderByName(name);
    }

    @PostMapping
    public Reader addNewReader(@RequestBody Reader reader) {
        return readerService.addNewReader(reader);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteReaderById(@PathVariable long id) {
        if (readerService.deleteById(id))
            return ResponseEntity.status(HttpStatus.OK).build();
        return ResponseEntity.notFound().build();
    }


}

package HW5_6.controller;

import HW5_6.model.Reader;
import HW5_6.service.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/reader")
@Tag(name = "Reader", description = "CRUD operation on readers in system")
public class ReaderController {
    @Autowired
    private ReaderService readerService;

    @GetMapping
    @Operation(summary = "Get all readers", description = "You take list of all readers")
    @ApiResponses(@ApiResponse(responseCode = "200"))
    public List<Reader> getAllReaders() {
        return readerService.getAllReaders();
    }
    @GetMapping(path = "/id/{id}")
    @Operation(summary = "Get reader by ID", description = "You take single reader by his ID")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Reader with this ID exist"),
                    @ApiResponse(responseCode = "404", description = """
                            There is no reader with such ID or you enter incorrect data
                            """)
            }

    )
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

package HW3_4.controller;

import HW3_4.repository.BookRepository;
import HW3_4.repository.IssueRepository;
import HW3_4.repository.ReaderRepository;
import HW3_4.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
@RequestMapping(path = "/table")
public class ControllerForTable {

    //region Fields:
    private final String bookPage = "books";
    private final String readerPage = "readers";
    private final String issuePage = "issues";
    private final String readersBookPage = "readers_books";
    private final String bookTableName = "List of books";
    private final String readerTableName = "List of readers";
    private final String issueTableName = "List of issues";
    private final String readersBookTableName = "List of reading books";
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private IssueRepository issueRepository;
    @Autowired
    private ReaderService readerService;
    //endregion

    @GetMapping(path = "/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookRepository.getALL());
        model.addAttribute("header", bookTableName);
        return bookPage;
    }
    @GetMapping(path = "/readers")
    public String getAllReaders(Model model) {
        model.addAttribute("readers", readerRepository.getAll());
        model.addAttribute("header", readerTableName);
        return readerPage;
    }
    @GetMapping(path = "/issues")
    public String getAllIssues(Model model) {
        Map<String, Object> attribute = Map.of(
                "issues", issueRepository.getAll(),
                "header", issueTableName
        );
        model.addAllAttributes(attribute);
        return issuePage;
    }
    @GetMapping(path = "/readers/{id}/book")
    public String getAllReadingBookByReader(@PathVariable(name = "id") long readerId, Model model) {
        model.addAttribute("header", readersBookTableName);
        model.addAttribute("books", readerService.getAllReadingBook(readerId));

        return readersBookPage;
    }



}

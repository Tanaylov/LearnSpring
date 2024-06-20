package HW5_6_7.controller.page;

import HW5_6_7.service.ReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/page/reader")
public class ReaderPageController {

    private final String readerPage = "readers";
    private final String readersBookPage = "readers_books";
    private final String readerTableName = "List of readers";
    private final String readersBookTableName = "List of reading books";
    @Autowired
    private ReaderService readerService;

    @GetMapping(path = "/all")
    public String getAllReaders(Model model) {
        model.addAttribute("readers", readerService.getAllReaders());
        model.addAttribute("header", readerTableName);
        return readerPage;
    }

    @GetMapping(path = "/{id}/book")
    public String getAllReadingBookByReader(@PathVariable(name = "id") long readerId, Model model) {
        model.addAttribute("header", readersBookTableName);
        model.addAttribute("books", readerService.getAllReadingBook(readerId));

        return readersBookPage;
    }
}

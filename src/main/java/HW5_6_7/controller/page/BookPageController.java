package HW5_6_7.controller.page;

import HW5_6_7.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/page/book")
public class BookPageController {

    private final String bookPage = "books";
    private final String bookTableName = "List of books";
    @Autowired
    private BookService bookService;

    @GetMapping(path = "/all")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("header", bookTableName);
        return bookPage;
    }

}

package ru.goldenAlf.digital_accounting_of_books.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.goldenAlf.digital_accounting_of_books.DAO.BookDAO;


@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO personDAO) {
        this.bookDAO = personDAO;
    }

    @GetMapping({"/", ""})
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "book/index";
    }
}
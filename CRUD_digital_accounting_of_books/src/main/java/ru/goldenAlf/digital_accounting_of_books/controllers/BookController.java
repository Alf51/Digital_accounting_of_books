package ru.goldenAlf.digital_accounting_of_books.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.goldenAlf.digital_accounting_of_books.DAO.BookDAO;
import ru.goldenAlf.digital_accounting_of_books.model.Book;
import ru.goldenAlf.digital_accounting_of_books.model.Person;


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

    @GetMapping({"/new", "/new/"})
    public String newBookCreate(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping()
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/new";
        }

        bookDAO.save(book);
        return "redirect:book/";
    }
}
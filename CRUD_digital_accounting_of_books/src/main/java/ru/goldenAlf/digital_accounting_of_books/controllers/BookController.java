package ru.goldenAlf.digital_accounting_of_books.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.goldenAlf.digital_accounting_of_books.DAO.BookDAO;
import ru.goldenAlf.digital_accounting_of_books.DAO.PersonDAO;
import ru.goldenAlf.digital_accounting_of_books.model.Book;
import ru.goldenAlf.digital_accounting_of_books.model.Person;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
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

    @PostMapping
    public String save(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/new";
        }

        bookDAO.save(book);
        return "redirect:book/";
    }

    @GetMapping({"/{id}", "/{id}/"})
    public String show(@PathVariable("id") int book_id, Model model, @ModelAttribute("person") Person person) {
        Book book = bookDAO.show(book_id).orElse(new Book());
        Person bookOwner = bookDAO.requestIdPersonWhoTookBook(book_id).orElse(new Person());

        model.addAttribute("bookOwner", bookOwner);
        model.addAttribute("book", book);
        model.addAttribute("people", personDAO.index());
        return "book/show";
    }

    @GetMapping({"/{id}/edit/", "/{id}/edit"})
    public String edit(@PathVariable("id") int id, Model model) {
        if (bookDAO.show(id).isEmpty()) {
            return "redirect:book/";
        }

        Book book = bookDAO.show(id).get();
        model.addAttribute("book", book);
        return "book/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         @PathVariable("id") int id,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "book/edit";
        }

        bookDAO.update(id, book);
        return "redirect:/book/";
    }

    @PatchMapping("/add/{book_id}")
    public String assignBook(@PathVariable("book_id") int book_id,
                             @ModelAttribute("person") Person person) {

        bookDAO.assignBook(person.getId(), book_id);
        return "redirect:/book/";
    }

    @PatchMapping("/releaseBook/{book_id}")
    public String releaseBook(@PathVariable("book_id") int book_id, Model model) {
        bookDAO.releaseBook(book_id);
        model.addAttribute("book", bookDAO.show(book_id).orElse(new Book()));
        return "redirect:/book/";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }
}
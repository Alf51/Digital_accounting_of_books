package org.goldenalf.digital_accounting_with_spring_boot.controllers;

import jakarta.validation.Valid;
import org.goldenalf.digital_accounting_with_spring_boot.model.Book;
import org.goldenalf.digital_accounting_with_spring_boot.model.Person;
import org.goldenalf.digital_accounting_with_spring_boot.services.BookService;
import org.goldenalf.digital_accounting_with_spring_boot.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final PersonService personService;

    @Autowired
    public BookController(BookService bookService, PersonService personService) {
        this.bookService = bookService;
        this.personService = personService;
    }

    //Страница со списком всех книг
    @GetMapping({"/", ""})
    public String index(Model model,
                        @RequestParam(name = "page", required = false) String countPage,
                        @RequestParam(name = "books_per_page", required = false) String countBooksPerPage,
                        @RequestParam(name = "sort_by_year", defaultValue = "false") String sortType) {
        model.addAttribute("books", bookService.index(countPage, countBooksPerPage, sortType));
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

        bookService.save(book);
        return "redirect:book/";
    }

    @GetMapping({"/{id}", "/{id}/"})
    public String show(@PathVariable("id") int book_id, Model model, @ModelAttribute("person") Person person) {
        Book book = bookService.show(book_id).orElse(new Book());
        Person bookOwner = bookService.requestIdPersonWhoTookBook(book_id).orElse(new Person());

        model.addAttribute("bookOwner", bookOwner);
        model.addAttribute("book", book);
        model.addAttribute("people", personService.index());
        return "book/show";
    }

    @GetMapping({"/{id}/edit/", "/{id}/edit"})
    public String edit(@PathVariable("id") int id, Model model) {
        if (bookService.show(id).isEmpty()) {
            return "redirect:book/";
        }

        Book book = bookService.show(id).get();
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

        bookService.update(id, book);
        return "redirect:/book/";
    }

    @PatchMapping("/add/{book_id}")
    public String assignBook(@PathVariable("book_id") int book_id,
                             @ModelAttribute("person") Person person) {

        bookService.assignBook(person.getId(), book_id);
        return "redirect:/book/";
    }

    @PatchMapping("/releaseBook/{book_id}")
    public String releaseBook(@PathVariable("book_id") int book_id, Model model) {
        bookService.releaseBook(book_id);
        model.addAttribute("book", bookService.show(book_id).orElse(new Book()));
        return "redirect:/book/" + book_id;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }

    @GetMapping({"/search/", "/search"})
    public String searchPag() {
        return "/book/search";
    }

    @PostMapping({"/search/", "/search"})
    public String makeSearch(@RequestParam(name = "keyword", required = false) String startingWith,
                             Model model) {

        List<Book> bookList = bookService.findBookByStartWithName(startingWith);
        model.addAttribute("books", bookList);

        return "/book/search";
    }
}
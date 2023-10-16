package org.goldenalf.digital_accounting_with_spring_boot.controllers;

import jakarta.validation.Valid;
import org.goldenalf.digital_accounting_with_spring_boot.model.Person;
import org.goldenalf.digital_accounting_with_spring_boot.services.BookService;
import org.goldenalf.digital_accounting_with_spring_boot.services.PersonService;
import org.goldenalf.digital_accounting_with_spring_boot.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonService personService;
    private final BookService bookService;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonService personService, BookService bookService, PersonValidator personValidator) {
        this.personService = personService;
        this.bookService = bookService;
        this.personValidator = personValidator;
    }

    @GetMapping({"/", ""})
    public String index(Model model) {
        model.addAttribute("people", personService.index());
        return "people/index";
    }

    @GetMapping({"/new", "/new/"})
    public String newPersonCreate(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        if (personService.show(id).isEmpty()) {
            return "redirect:/people";
        }

        //показать конкретного пользователя
        Person person = personService.show(id).get();
        model.addAttribute("person", person);
        model.addAttribute("books", bookService.showBooksTakenPerson(id));
        return "people/show";
    }

    @PostMapping
    public String save(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/new";
        }

        personService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        if (personService.show(id).isEmpty()) {
            return "redirect:/people";
        }
        System.out.println("================");
        Person person = personService.show(id).get();
        model.addAttribute("person", person);
        return "people/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         @PathVariable("id") int id,
                         BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        personService.update(id, person);
        return "redirect:/people/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personService.delete(id);
        return "redirect:/people";
    }
}

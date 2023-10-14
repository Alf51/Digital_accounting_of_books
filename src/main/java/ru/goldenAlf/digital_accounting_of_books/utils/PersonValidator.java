package ru.goldenAlf.digital_accounting_of_books.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.goldenAlf.digital_accounting_of_books.model.Person;
import ru.goldenAlf.digital_accounting_of_books.services.PersonService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    @Autowired
    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (!isValidDate(person.getBirthday())) {
            errors.rejectValue("birthday", "", "Неправельный формат даты, введи дату dd.MM.yyyy");
        }

        if (!person.getFullName().matches("[А-Яа-я]*?\\s[А-Яа-я]*?\\s[А-Яа-я]*")) {
            errors.rejectValue("fullName", "", "Напишите польностью ФИО");
        }

        if (personService.show(person.getFullName()).isPresent()) {
            if (personService.show(person.getFullName()).get().getId() != person.getId()) {
                errors.rejectValue("fullName", "", "Такой человек уже зарегистрирован");
            }
        }


    }

    public boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}

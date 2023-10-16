package org.goldenalf.digital_accounting_with_spring_boot.utils;

import org.goldenalf.digital_accounting_with_spring_boot.model.Person;
import org.goldenalf.digital_accounting_with_spring_boot.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
            errors.rejectValue("birthday", "", "Неправильный формат даты, введи дату dd.MM.yyyy");
        }

        if (!person.getFullName().matches("[А-Яа-я]*?\\s[А-Яа-я]*?\\s[А-Яа-я]*")) {
            errors.rejectValue("fullName", "", "Напишите полностью ФИО");
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

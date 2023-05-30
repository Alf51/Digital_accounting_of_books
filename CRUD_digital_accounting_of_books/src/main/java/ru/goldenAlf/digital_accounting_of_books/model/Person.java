package ru.goldenAlf.digital_accounting_of_books.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class Person {
    private int id;

    @NotEmpty(message = "Полное имя не может быть пустым")
    @Pattern(regexp = "[А-Яа-я]*?\\s[А-Яа-я]*?\\s[А-Яа-я]*", message = "Напишите польностью ФИО")
    private String fullName;
    //@DateTimeFormat(pattern = "\\d{4}-\\d{2}-\\d{2}")
    private LocalDate birthday;

    public Person() {
    }

    public Person(int id, String fullName, LocalDate birthday) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}

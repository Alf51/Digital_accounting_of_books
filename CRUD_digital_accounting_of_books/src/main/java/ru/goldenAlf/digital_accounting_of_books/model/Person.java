package ru.goldenAlf.digital_accounting_of_books.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class Person {
    private int id;

    @NotEmpty(message = "Полное имя не может быть пустым")
    @Pattern(regexp = "[А-Яа-я]*?\\s[А-Яа-я]*?\\s[А-Яа-я]*", message = "Напишите польностью ФИО")
    private String fullName;
    @Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d", message = "Неправельный формат даты, введи дату dd.MM.yyyy")
    private String birthday;

    // Конструктор по умолчанию нужен для Spring
    public Person() {
    }

    public Person(int id, String fullName, String birthday) {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}

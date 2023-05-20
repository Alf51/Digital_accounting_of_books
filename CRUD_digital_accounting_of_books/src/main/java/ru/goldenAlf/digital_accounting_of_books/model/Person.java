package ru.goldenAlf.digital_accounting_of_books.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public class Person {
    private int id;

    @NotEmpty(message = "Полное имя не может быть пустым")
    private String fullName;
    @DateTimeFormat(pattern = "\\d{4}-\\d{2}-\\d{2}")
    private LocalDateTime birthday;

    public Person() {
    }

    public Person(int id, String fullName, LocalDateTime birthday) {
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

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }
}

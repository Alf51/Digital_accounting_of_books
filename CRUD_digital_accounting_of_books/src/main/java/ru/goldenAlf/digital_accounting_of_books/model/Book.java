package ru.goldenAlf.digital_accounting_of_books.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Book {
    private int id;

    @NotEmpty(message = "Название книги обязательно")
    private String name;

    private String author;
    @DateTimeFormat(pattern = "\\d{4}-\\d{2}-\\d{2}")
    private LocalDateTime year;

    public Book() {
    }

    public Book(int id, String name, String author, LocalDateTime year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getYear() {
        return year;
    }

    public void setYear(LocalDateTime year) {
        this.year = year;
    }
}

package ru.goldenAlf.digital_accounting_of_books.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Название книги обязательно")
    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "year")
    private LocalDate year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    // Конструктор по умолчанию нужен для Spring
    public Book() {
    }

    public Book(int id, String name, String author, LocalDate year) {
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

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}

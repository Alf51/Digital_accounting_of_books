package org.goldenalf.digital_accounting_with_spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Полное имя не может быть пустым")
    @Pattern(regexp = "[А-Яа-я]*?\\s[А-Яа-я]*?\\s[А-Яа-я]*", message = "Напишите польностью ФИО")
    @Column(name = "fullname")
    private String fullName;

    @Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d", message = "Неправельный формат даты, введи дату dd.MM.yyyy")
    @Column(name = "birthday")
    private String birthday;


    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
    private List<Book> bookList;

    // Конструктор по умолчанию нужен для Spring
    public Person() {
    }

    public Person(int id, String fullName, String birthday) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public void addBookInList(Book book) {
        if (this.bookList == null) {
            this.bookList  = new ArrayList<>();
        }
        bookList.add(book);
    }
}

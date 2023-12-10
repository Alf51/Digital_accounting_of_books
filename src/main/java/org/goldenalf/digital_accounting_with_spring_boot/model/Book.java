package org.goldenalf.digital_accounting_with_spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "book")
@Getter
@Setter
@EqualsAndHashCode
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

    @Column(name = "date_of_issue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfIssue;


    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Transient
    private boolean isOverdue;

    // Конструктор по умолчанию нужен для Spring
    public Book() {
    }

    public Book(int id, String name, String author, LocalDate year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }


    public boolean isOverdue() {
        if (this.dateOfIssue == null) {
            return false;
        }

        long diffInMillies = new Date().getTime() - this.dateOfIssue.getTime();
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        return diff >= 10;
    }
}

package ru.goldenAlf.digital_accounting_of_books.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goldenAlf.digital_accounting_of_books.model.Book;
import ru.goldenAlf.digital_accounting_of_books.model.Person;
import ru.goldenAlf.digital_accounting_of_books.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    private final PersonService personService;

    @Autowired
    public BookService(BookRepository bookRepository, PersonService personService) {
        this.bookRepository = bookRepository;
        this.personService = personService;
    }

    //Возвращает список всех книг
    public List<Book> index() {
        return bookRepository.findAll();
    }

    public Optional<Book> show(int id) {
        return bookRepository.findById(id);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Book bookUpdate) {
        Book book = bookRepository.findById(id).get();
        book.setPerson(bookUpdate.getPerson());
        book.setAuthor(bookUpdate.getAuthor());
        book.setName(bookUpdate.getName());
        book.setYear(bookUpdate.getYear());
    }

    //Показать книги, которые взял конкретный пользователь
    public List<Book> showBooksTakenPerson(int personId) {
        return bookRepository.findByPersonId(personId);
    }

    //Возвращает человека, который взял конкретную книгу
    public Optional<Person> requestIdPersonWhoTookBook(int id_book) {
        return personService.index()
                .stream()
                .filter(person -> person.getBookList().contains(bookRepository.findById(id_book).get()))
                .findAny();
    }

    @Transactional
    public void assignBook(int person_id, int book_id) {
        Person person = personService.show(person_id).get();
        Book book = bookRepository.findById(book_id).get();
        book.setPerson(person);
        person.addBookInList(book);
    }

    @Transactional
    public void releaseBook(int book_id) {
        Book book = bookRepository.findById(book_id).get();
        Person person = book.getPerson();
        person.getBookList().remove(book);
        book.setPerson(null);
    }
}

package org.goldenalf.digital_accounting_with_spring_boot.services;


import org.goldenalf.digital_accounting_with_spring_boot.model.Book;
import org.goldenalf.digital_accounting_with_spring_boot.model.Person;
import org.goldenalf.digital_accounting_with_spring_boot.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.Date;
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

    public List<Book> index(Sort sort) {
        return bookRepository.findAll(sort);
    }

    //Возвращает определённое число книг на определённой странице с учётом пагинации и сортировки.
    public List<Book> index(String page, String booksPerPage, String sortType) {
        boolean isSort = false;
        try {
            isSort = Boolean.parseBoolean(sortType);
            int countPage = Integer.parseInt(page);
            int countBooksPerPage = Integer.parseInt(booksPerPage);
            return bookRepository.findAll(PageRequest.of(countPage,
                            countBooksPerPage,
                            getSortTypeByYear(isSort)))
                    .getContent();

        } catch (NumberFormatException e) {
            return index((getSortTypeByYear(isSort)));
        }
    }

    private static Sort getSortTypeByYear(boolean isSort) {
        return isSort ? Sort.by("year") : Sort.unsorted();
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

        // Добавляем книгу, котора не находиться в persistence context
        bookUpdate.setPerson(book.getPerson()); //Чтобы не терялась связь при обновление
        bookUpdate.setId(id);

        bookRepository.save(bookUpdate);
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
        book.setDateOfIssue(new Date());
        person.addBookInList(book);
    }

    @Transactional
    public void releaseBook(int book_id) {
        Book book = bookRepository.findById(book_id).get();
        Person person = book.getPerson();
        person.getBookList().remove(book);
        book.setPerson(null);
        book.setDateOfIssue(null);
    }

    public List<Book> findBookByStartWithName(String startingWith) {
        return startingWith.isEmpty() ? Collections.emptyList() : bookRepository.findByNameStartingWith(startingWith);
    }
}

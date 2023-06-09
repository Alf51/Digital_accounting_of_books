package ru.goldenAlf.digital_accounting_of_books.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.goldenAlf.digital_accounting_of_books.model.Book;
import ru.goldenAlf.digital_accounting_of_books.model.Person;
import ru.goldenAlf.digital_accounting_of_books.utils.PersonMapper;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> show(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE id=?", new BeanPropertyRowMapper<>(Book.class), id)
                .stream()
                .findAny();
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO book(name, author, year) VALUES (?, ?, ?)",
                book.getName(),
                book.getAuthor(),
                book.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public void update(int id, Book bookUpdate) {
        jdbcTemplate.update("UPDATE book SET name=?, author=?, year=?  WHERE id=?",
                bookUpdate.getName(),
                bookUpdate.getAuthor(),
                bookUpdate.getYear(),
                id);
    }

    public List<Book> showBooksTakenPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE person_id=?",
                new BeanPropertyRowMapper<>(Book.class),
                id);
    }

    public Optional<Person> requestIdPersonWhoTookBook(int id_book) {
        return jdbcTemplate.query("SELECT * from book join person on book.person_id = person.id where book.id=?",
                new PersonMapper(),
                id_book).stream().findAny();
    }


    public void assignBook(int person_id, int book_id) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?",
                person_id, book_id);
    }

    public void releaseBook(int book_id) {
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE id=?", book_id);
    }
}

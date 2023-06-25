package ru.goldenAlf.digital_accounting_of_books.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.goldenAlf.digital_accounting_of_books.model.Person;
import ru.goldenAlf.digital_accounting_of_books.utils.PersonMapper;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Список всех людей
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * From person", new PersonMapper());
    }

    //Возвращает конкретного человека
    public Optional<Person> show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                new PersonMapper(),
                id).stream().findAny();
    }

    public Optional<Person> show(String fullName) {
        return jdbcTemplate.query("SELECT * FROM person WHERE fullname=?",
                new PersonMapper(),
                fullName).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO person(fullname, birthday) VALUES (?, ?)",
                person.getFullName(),
                person.getBirthday());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public void update(int id, Person personUpdate) {
        jdbcTemplate.update("UPDATE person SET fullname=?, birthday=? WHERE id=?",
                personUpdate.getFullName(),
                personUpdate.getBirthday(),
                id);
    }
}

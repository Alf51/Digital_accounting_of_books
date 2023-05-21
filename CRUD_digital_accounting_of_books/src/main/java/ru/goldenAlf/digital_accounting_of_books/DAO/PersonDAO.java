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
        jdbcTemplate.query("SELECT * From person", new PersonMapper());
        return null;
    }

    //Возвращает конкретного человека
    public Optional<Person> show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                new PersonMapper(),
                id).stream().findAny();
    }

    public void save(int id, Person personUpdate) {
        jdbcTemplate.query("INSERT INTO person(id, fullname, birthday) VALUES (?, ?, ?)", new PersonMapper());
    }

    public void delete(int id) {
        jdbcTemplate.query("DELETE FROM person WHERE id=?", new PersonMapper(), id);
    }

    public void update(int id, Person personUpdate) {
        jdbcTemplate.query("UPDATE person SET fullname=?, birthday=? WHERE id=?", new PersonMapper(),
                personUpdate.getFullName(),
                personUpdate.getBirthday(),
                id);
    }
}

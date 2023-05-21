package ru.goldenAlf.digital_accounting_of_books.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.goldenAlf.digital_accounting_of_books.model.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //TODO: Список всех людей
    public List<Person> index() {
        jdbcTemplate.query("SELECT * From person", new BeanPropertyRowMapper<>());
        return null;
    }

    //TODO: Возвращает конкретного человека
    public Optional<Person> show() {
        return Optional.empty();
    }

    public void save(Person person) {

    }

    public void delete(int id) {

    }

    public void update(Person person) {

    }
}

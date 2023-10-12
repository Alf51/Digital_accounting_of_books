package ru.goldenAlf.digital_accounting_of_books.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.goldenAlf.digital_accounting_of_books.model.Person;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setFullName(resultSet.getString("fullname"));

        person.setBirthday(resultSet.getString("birthday"));
        return person;
    }
}

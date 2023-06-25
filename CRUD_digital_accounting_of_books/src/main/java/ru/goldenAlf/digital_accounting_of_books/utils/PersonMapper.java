package ru.goldenAlf.digital_accounting_of_books.utils;

import org.springframework.jdbc.core.RowMapper;
import ru.goldenAlf.digital_accounting_of_books.model.Person;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setFullName(resultSet.getString("fullname"));

//        Date date = resultSet.getDate("birthday");
//        LocalDate localDateTime = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
        person.setBirthday(resultSet.getString("birthday"));
        return person;
    }
}

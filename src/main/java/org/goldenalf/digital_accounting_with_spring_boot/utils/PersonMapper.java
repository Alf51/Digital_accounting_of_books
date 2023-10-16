package org.goldenalf.digital_accounting_with_spring_boot.utils;

import org.goldenalf.digital_accounting_with_spring_boot.model.Person;
import org.springframework.jdbc.core.RowMapper;
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

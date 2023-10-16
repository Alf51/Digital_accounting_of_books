package ru.goldenAlf.digital_accounting_of_books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goldenAlf.digital_accounting_of_books.model.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByFullName(String fullName);
}

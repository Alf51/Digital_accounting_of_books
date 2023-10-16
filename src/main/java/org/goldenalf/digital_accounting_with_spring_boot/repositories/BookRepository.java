package org.goldenalf.digital_accounting_with_spring_boot.repositories;

import org.goldenalf.digital_accounting_with_spring_boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByPersonId(int personId);

    List<Book> findByNameStartingWith(String startingWith);
}

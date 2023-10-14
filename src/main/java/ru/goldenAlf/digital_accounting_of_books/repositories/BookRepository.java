package ru.goldenAlf.digital_accounting_of_books.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.goldenAlf.digital_accounting_of_books.model.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByPersonId(int personId);
}

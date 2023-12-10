package org.goldenalf.digital_accounting_with_spring_boot.services;

import org.goldenalf.digital_accounting_with_spring_boot.model.Book;
import org.goldenalf.digital_accounting_with_spring_boot.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(
        MockitoExtension.class
)
class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;


    @Test
    public void shouldReturnAllBooks() {
        //given
        List<Book> books = getBooks();
        Mockito.when((bookRepository.findAll())).thenReturn(books);

        //when
        List<Book> result = bookService.index();

        //then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(books.size(), result.size());
        Assertions.assertEquals(books, result);
    }

    private List<Book> getBooks() {
        Book book1 = new Book(1, "Грокаем алгоритмы", "Уэлс", LocalDate.of(2022, 11, 21));
        Book book2 = new Book(2, "Нейромант", "Гибс", LocalDate.of(1995, 11, 11));
        return List.of(book1, book2);
    }
}
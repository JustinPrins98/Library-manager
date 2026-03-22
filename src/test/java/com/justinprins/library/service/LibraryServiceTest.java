package com.justinprins.library.service;

import com.justinprins.library.domain.Book;
import com.justinprins.library.repository.BookRepository;
import com.justinprins.library.repository.InMemoryBookRepository;
import com.justinprins.library.service.LibraryService;
import com.justinprins.library.service.LibraryServiceImpl;
import com.justinprins.library.exception.BookNotFoundException;
import com.justinprins.library.exception.NoAvailableCopiesException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryServiceTest {

    @Test
    void borrowBook_decrementsAvailableCopies() {
        // Arrange
        Book bookOne = new Book(1, "borrowed", "Stephenson", 3);

        BookRepository repository = new InMemoryBookRepository();
        LibraryService service = new LibraryServiceImpl(repository);

        repository.save(bookOne);

        // Act
        service.borrowBook(1);

        // Assert
        Book updatedBook = repository.findById(1);
        assertEquals(2, updatedBook.getAvailableCopies());
    }

    @Test
    void borrowBook_throwsBookNotFoundException() {

        // Arrange
        BookRepository repository = new InMemoryBookRepository();
        LibraryService service = new LibraryServiceImpl(repository);

        // Act

        assertThrows(BookNotFoundException.class, () -> service.borrowBook(999));

    }

    @Test
    void borrowBook_throwsNoAvailableCopiesException() {
        Book book = new Book(2, "Nocopies", "Johnson", 0);

        BookRepository repository = new InMemoryBookRepository();
        LibraryService service = new LibraryServiceImpl(repository);

        repository.save(book);

        NoAvailableCopiesException ex = assertThrows(NoAvailableCopiesException.class, () -> service.borrowBook(2));
        assertEquals("Book is not available for borrowing", ex.getMessage());
    }

    @Test
    void listAvailableBooks_returnsOnlyAvailableBooks() {
        Book bookOne = new Book(1, "Harry potter", "Rowling", 3);
        Book bookTwo = new Book(2, "Harry potter 2", "Rowling", 5);
        Book bookThree = new Book(3, "Barry potter", "Rawling", 0);

        BookRepository repository = new InMemoryBookRepository();
        LibraryService service = new LibraryServiceImpl(repository);

        repository.save(bookOne);
        repository.save(bookTwo);
        repository.save(bookThree);

        List<Book> result = service.listAvailableBooks();

        assertEquals(2, result.size());

        assertTrue(result.stream().anyMatch(book -> book.getId() == 1));
        assertTrue(result.stream().anyMatch(book -> book.getId() == 2));

        assertFalse(result.stream().anyMatch(book -> book.getId() == 3));
    }

}
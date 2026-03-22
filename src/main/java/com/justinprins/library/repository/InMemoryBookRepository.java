package com.justinprins.library.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

import com.justinprins.library.domain.Book;
import com.justinprins.library.exception.BookNotFoundException;

public class InMemoryBookRepository implements BookRepository {

    private final Map<Integer, Book> books = new HashMap<>();

    @Override
    public Book findById(int id) {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException("Book not found " + id);
        }

        return books.get(id);
    }

    public void save(Book book) {
        books.put(book.getId(), book);
    }

    public Collection<Book> findAll() {
        return books.values();
    }

}
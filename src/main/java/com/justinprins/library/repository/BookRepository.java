package com.justinprins.library.repository;

import java.util.Collection;

import com.justinprins.library.domain.Book;

public interface BookRepository {

    Book findById(int id);

    void save(Book book);

    Collection<Book> findAll();

}

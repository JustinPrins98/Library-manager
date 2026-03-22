package com.justinprins.library.service;

import com.justinprins.library.domain.*;
import java.util.List;

public interface LibraryService {

    void borrowBook(int id);

    void returnBook(int id);

    List<Book> listAvailableBooks();

}

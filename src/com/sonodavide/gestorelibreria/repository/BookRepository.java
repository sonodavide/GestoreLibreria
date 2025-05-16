package com.sonodavide.gestorelibreria.repository;

import com.sonodavide.gestorelibreria.model.Book;

import java.util.List;

public interface BookRepository {
    Book findByIsbn(String isbn);
    List<Book> findAll();
    Book add(Book book);
    List<Book> addAll(List<Book> newBooks);
    Book update(Book book);
    Book delete(Book book);
}

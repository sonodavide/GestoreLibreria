package com.sonodavide.gestorelibreria.model.ObservableList;

import com.sonodavide.gestorelibreria.model.BookDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ObservableList implements Subject{
    private final List<Observer> observers = new ArrayList<>();
    private List<BookDto> books;

    public ObservableList() {
        books = new ArrayList<>();
    }
    public ObservableList(List<BookDto> books) {
        this.books = books;
    }
    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    public void addBook(BookDto book) {
        books.add(book);
        notifyObservers();
    }

    public void deleteBook(BookDto book) {
        books.remove(book);
        notifyObservers();
    }

    public List<BookDto> getBooks() {
        return Collections.unmodifiableList(books);

    }



    @Override
    public void notifyObservers() {

    }
}

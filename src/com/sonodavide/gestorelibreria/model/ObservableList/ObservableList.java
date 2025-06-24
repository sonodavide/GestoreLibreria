package com.sonodavide.gestorelibreria.model.ObservableList;

import com.sonodavide.gestorelibreria.model.BookDto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ObservableList<T> implements Subject{
    private final List<Observer> observers = new ArrayList<>();
    private List<T> list;

    public ObservableList() {
        list = new ArrayList<>();
    }
    public ObservableList(List<T> list) {
        this.list = list;
    }
    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        this.observers.remove(observer);
    }

    public void addElement(T element) {
        list.add(element);
        notifyObservers();
    }

    public void deleteElement(T element) {
        list.remove(element);
        notifyObservers();
    }

    public List<T> getlist() {
        return Collections.unmodifiableList(list);

    }
    public int getSize() {
        return list.size();
    }


    @Override
    public void notifyObservers() {
        for(Observer observer : observers)
            observer.update();
    }
}

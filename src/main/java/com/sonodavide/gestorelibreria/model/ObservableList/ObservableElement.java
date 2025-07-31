package com.sonodavide.gestorelibreria.model.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ObservableElement<T> implements Subject{
    private final List<Observer> observers = new ArrayList<>();
    private T element;
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers)
            observer.update();
    }
    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
        notifyObservers();
    }
    public void unsetElement() {
        this.element = null;
        notifyObservers();
    }
}

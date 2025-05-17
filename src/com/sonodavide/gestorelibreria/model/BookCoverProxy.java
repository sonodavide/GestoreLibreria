package com.sonodavide.gestorelibreria.model;

import com.sonodavide.gestorelibreria.util.Pair;

import java.awt.*;
import java.nio.file.Path;

public class BookCoverProxy implements BookCover {
    private BookCoverImpl bookCover;
    private Pair<Integer> size;
    private Path bookCoverPath;
    BookCoverProxy(Path bookCoverPath) {
        this.bookCoverPath = bookCoverPath;
        size = new Pair<>(0, 0); //default size
    }
    @Override
    public void draw() {
        if(this.bookCover == null){
            bookCover = new BookCoverImpl(bookCoverPath);
        }
        bookCover.draw();
    }

    @Override
    public Pair<Integer> getSize() {
        if(bookCover == null){
            return this.size;
        } else{
            return bookCover.getSize();
        }
    }
}

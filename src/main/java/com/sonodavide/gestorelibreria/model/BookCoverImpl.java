package com.sonodavide.gestorelibreria.model;

import com.sonodavide.gestorelibreria.util.Pair;

import java.nio.file.Path;

public class BookCoverImpl implements BookCover {
    private Pair<Integer> size;
    private Path path;
    public BookCoverImpl(Path path) {
        this.path = path;
    }
    @Override
    public void draw() {
        // load cover image.
    }

    @Override
    public Pair<Integer> getSize() {
        return size;
    }
}

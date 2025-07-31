package com.sonodavide.gestorelibreria.model;

import com.sonodavide.gestorelibreria.util.Pair;


public interface BookCover {
    void draw();
    Pair<Integer> getSize();
}

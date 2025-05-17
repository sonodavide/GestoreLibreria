package com.sonodavide.gestorelibreria.model;

import com.sonodavide.gestorelibreria.util.Pair;

import java.awt.*;


public interface BookCover {
    void draw();
    Pair<Integer> getSize();
}

package com.sonodavide.gestorelibreria;

import com.sonodavide.gestorelibreria.controller.LibreriaPersonaleController;
import com.sonodavide.gestorelibreria.model.Book;
import com.sonodavide.gestorelibreria.model.ReadStatus;
import com.sonodavide.gestorelibreria.model.Review;
import com.sonodavide.gestorelibreria.view.LibreriaPersonaleView;

import java.util.Arrays;

public class GestoreLibreriaApp {

    public static void main(String[] args) {
        new LibreriaPersonaleController(new LibreriaPersonaleView());
    }
}

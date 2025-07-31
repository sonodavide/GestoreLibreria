package com.sonodavide.gestorelibreria;

import com.sonodavide.gestorelibreria.controller.LibreriaPersonaleController;
import com.sonodavide.gestorelibreria.view.LibreriaPersonaleView;

public class GestoreLibreriaApp {

    public static void main(String[] args) {
        new LibreriaPersonaleController(new LibreriaPersonaleView());
    }
}

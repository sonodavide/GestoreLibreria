package com.sonodavide.gestorelibreria.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private String isbn;
    private String title;
    private int pages;

}

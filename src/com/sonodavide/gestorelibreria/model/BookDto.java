package com.sonodavide.gestorelibreria.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class BookDto {
    private String isbn;
    private String title;
    private int pages;
    private List<String> authors;
    private String publisher;
    private ReadStatus readStatus;
    private List<String> genres;
    private Review review;

}

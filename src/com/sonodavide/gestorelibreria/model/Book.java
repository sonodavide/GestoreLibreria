package com.sonodavide.gestorelibreria.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Book {
    private String isbn;
    private String title;
    private String author;
    private String publisher;
    private int pages;
    private ReadStatus readStatus;
    private String genre;
    private Review review;
    private BookCover cover;


    public static class Builder {
        private String isbn;
        private String title;
        private int pages;
        private String author="";
        private String publisher="";
        private ReadStatus readStatus=ReadStatus.NOT_READ;
        private String genre="";
        private Review review=null;

        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }
        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }
        public Builder setPages(int pages) {
            if (pages < 1) throw new IllegalArgumentException("pages cannot be less than 1");
            this.pages = pages;
            return this;
        }
        public Builder setReadStatus(ReadStatus readStatus) {
            this.readStatus = readStatus;
            return this;
        }
        public Builder setGenre(String genre) {
            this.genre = genre;
            return this;
        }
        public Builder setReview(Review review) {
            this.review = review;
            return this;
        }
        public Book build() {
            return new Book(this);
        }
    }
    private Book(Builder builder) {
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.author = builder.author;
        this.publisher = builder.publisher;
        this.pages = builder.pages;
        this.readStatus = builder.readStatus;
        this.genre = builder.genre;
        this.review = builder.review;

    }


}

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
    private List<String> authors;
    private String publisher;
    private int pages;
    private ReadStatus readStatus;
    private List<String> genres;
    private Review review;
    private BookCover cover;


    public static class Builder {
        private String isbn;
        private String title;
        private int pages;
        private List<String> authors = new ArrayList<>();
        private String publisher = "";
        private ReadStatus readStatus = ReadStatus.NOT_READ;
        private List<String> genres = new ArrayList<>();
        private Review review = null;

        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAuthors(List<String> authors) {
            this.authors = new ArrayList<>(authors);
            return this;
        }

        public Builder addAuthor(String author) {
            this.authors.add(author);
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

        public Builder setGenres(List<String> genres) {
            this.genres = new ArrayList<>(genres);
            return this;
        }

        public Builder addGenre(String genre) {
            this.genres.add(genre);
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
        this.authors = new ArrayList<>(builder.authors);
        this.publisher = builder.publisher;
        this.pages = builder.pages;
        this.readStatus = builder.readStatus;
        this.genres = new ArrayList<>(builder.genres);
        this.review = builder.review;
    }
}
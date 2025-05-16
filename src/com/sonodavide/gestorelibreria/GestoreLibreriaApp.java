package com.sonodavide.gestorelibreria;

import com.sonodavide.gestorelibreria.model.Book;
import com.sonodavide.gestorelibreria.model.ReadStatus;
import com.sonodavide.gestorelibreria.model.Review;

import java.util.Arrays;

public class GestoreLibreriaApp {

    public static void main(String[] args) {
        // Creazione di un oggetto Review
        Review review1 = new Review();
        review1.setStars(5);
        review1.setComment("Un libro fantastico!");

        // Creazione di un libro utilizzando il Builder
        Book book1 = new Book.Builder()
                .setIsbn("978-8812345678")
                .setTitle("Il Signore degli Anelli")
                .setAuthor("J.R.R. Tolkien")
                .setPublisher("Bompiani")
                .setPages(1200)
                .setReadStatus(ReadStatus.READ)
                .setGenres(Arrays.asList("Fantasy", "Adventure"))
                .setReview(review1)
                .build();

        System.out.println("Libro 1:");
        System.out.println(book1);
        System.out.println();

        // Creazione di un altro libro
        Book book2 = new Book.Builder()
                .setIsbn("978-0123456789")
                .setTitle("Fondazione")
                .setAuthor("Isaac Asimov")
                .setPublisher("Mondadori")
                .setPages(300)
                .setReadStatus(ReadStatus.READING)
                .setGenres(Arrays.asList("Science Fiction"))
                .build();

        System.out.println("Libro 2:");
        System.out.println(book2);
    }
}

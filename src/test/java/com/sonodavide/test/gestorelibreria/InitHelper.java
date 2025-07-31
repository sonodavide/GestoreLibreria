package com.sonodavide.test.gestorelibreria;

import com.sonodavide.gestorelibreria.model.BookDto;
import com.sonodavide.gestorelibreria.model.ReadStatus;
import com.sonodavide.gestorelibreria.model.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitHelper {
    public static List<BookDto> initBooksExample() {
        List<BookDto> books = new ArrayList<>();

        // Libro 1
        BookDto book1 = new BookDto();
        book1.setIsbn("978-1234567890");
        book1.setTitle("Il Signore degli Anelli");
        book1.setPages(1216);
        book1.setAuthors(Arrays.asList("J.R.R. Tolkien"));
        book1.setPublisher("Bompiani");
        book1.setReadStatus(ReadStatus.READ);
        book1.setGenres(Arrays.asList("Fantasy", "Avventura"));
        book1.setReview(new Review(5, "Epico e coinvolgente"));

        // Libro 2 (errore voluto: autore sbagliato per test)
        BookDto book2 = new BookDto();
        book2.setIsbn("978-0451524935");
        book2.setTitle("1984");
        book2.setPages(328);
        book2.setAuthors(Arrays.asList("George Orwell"));
        book2.setPublisher("Mondadori");
        book2.setReadStatus(ReadStatus.READING);
        book2.setGenres(Arrays.asList("Distopia", "Politico"));
        book2.setReview(new Review(4, "Molto attuale e inquietante"));

        // Libro 3 (stesso titolo e autore di book4 ma con dettagli diversi)
        BookDto book3 = new BookDto();
        book3.setIsbn("978-8804681963");
        book3.setTitle("Il nome della rosa");
        book3.setPages(512);
        book3.setAuthors(Arrays.asList("Umberto Eco"));
        book3.setPublisher("Bompiani");
        book3.setReadStatus(ReadStatus.NOT_READ);
        book3.setGenres(Arrays.asList("Giallo storico"));
        book3.setReview(new Review(0, ""));

        // Libro 4 (doppione quasi identico a book3 ma con un autore in più e un genere aggiunto)
        BookDto book4 = new BookDto();
        book4.setIsbn("978-8804681963"); // stesso ISBN per test duplicati
        book4.setTitle("Il nome della rosa");
        book4.setPages(512);
        book4.setAuthors(Arrays.asList("Umberto Eco", "Alberto Albani"));
        book4.setPublisher("Bompiani");
        book4.setReadStatus(ReadStatus.NOT_READ);
        book4.setGenres(Arrays.asList("Giallo storico", "Fantascientifico"));
        book4.setReview(new Review(0, ""));

        // Libro 5 (stesso titolo di book1 ma edizione diversa)
        BookDto book5 = new BookDto();
        book5.setIsbn("978-9999999999");
        book5.setTitle("Il Signore degli Anelli");
        book5.setPages(1220);
        book5.setAuthors(Arrays.asList("J.R.R. Tolkien"));
        book5.setPublisher("HarperCollins");
        book5.setReadStatus(ReadStatus.READING);
        book5.setGenres(Arrays.asList("Fantasy"));
        book5.setReview(new Review(3, "Bella edizione ma traduzione discutibile"));

        // Libro 6 (completamente diverso, senza review)
        BookDto book6 = new BookDto();
        book6.setIsbn("978-8845292614");
        book6.setTitle("Sapiens: Da animali a dèi");
        book6.setPages(528);
        book6.setAuthors(Arrays.asList("Yuval Noah Harari"));
        book6.setPublisher("Bompiani");
        book6.setReadStatus(ReadStatus.READING);
        book6.setGenres(Arrays.asList("Saggistica", "Storia"));
        book6.setReview(new Review(0, ""));

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);

        return books;
    }
}
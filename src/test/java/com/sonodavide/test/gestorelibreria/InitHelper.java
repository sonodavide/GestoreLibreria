package com.sonodavide.test.gestorelibreria;

import com.sonodavide.gestorelibreria.model.BookDto;
import com.sonodavide.gestorelibreria.model.ReadStatus;
import com.sonodavide.gestorelibreria.model.Review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitHelper {
    public static List<BookDto> initBooksExample(){
        List<BookDto> books = new ArrayList<>();

        BookDto book1 = new BookDto();
        book1.setIsbn("978-1234567890");
        book1.setTitle("Il Signore degli Anelli");
        book1.setPages(1216);
        book1.setAuthors(Arrays.asList("J.R.R. Tolkien"));
        book1.setPublisher("Bompiani");
        book1.setReadStatus(ReadStatus.READ);
        book1.setGenres(Arrays.asList("Fantasy"));
        book1.setReview(new Review(5, "Epico e coinvolgente"));

        BookDto book2 = new BookDto();
        book2.setIsbn("978-0451524935");
        book2.setTitle("1984");
        book2.setPages(328);
        book2.setAuthors(Arrays.asList("George Orwell"));
        book2.setPublisher("Mondadori");
        book2.setReadStatus(ReadStatus.READING);
        book2.setGenres(Arrays.asList("Distopia"));
        book2.setReview(new Review(4, "Molto attuale e inquietante"));

        BookDto book3 = new BookDto();
        book3.setIsbn("978-8804681963");
        book3.setTitle("Il nome della rosa");
        book3.setPages(512);
        book3.setAuthors(Arrays.asList("Umberto Eco"));
        book3.setPublisher("Bompiani");
        book3.setReadStatus(ReadStatus.NOT_READ);
        book3.setGenres(Arrays.asList("Giallo storico"));
        book3.setReview(new Review(0, ""));

        books.add(book1);
        books.add(book2);
        books.add(book3);

        return books;
    }

}

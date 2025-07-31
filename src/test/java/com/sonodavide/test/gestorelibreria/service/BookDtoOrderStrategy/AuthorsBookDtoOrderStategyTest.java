package com.sonodavide.test.gestorelibreria.service.BookDtoOrderStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;
import com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy.AuthorsBookDtoOrderStategy;
import com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy.GenresBookDtoOrderStrategy;
import com.sonodavide.test.gestorelibreria.InitHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorsBookDtoOrderStategyTest {
    AuthorsBookDtoOrderStategy strategy;
    private List<BookDto> books;
    @BeforeEach
    public void setup(){
        books = InitHelper.initBooksExample();
    }

    @Test
    public void testSort(){
        strategy = new AuthorsBookDtoOrderStategy();
        List<BookDto>res = strategy.execute(books, false);

        assertEquals(books.size(), res.size());
        assertEquals(books.get(0), res.get(0));
        assertEquals(books.get(1), res.get(1));
        assertEquals(books.get(2), res.get(3));
        assertEquals(books.get(3), res.get(2));

    }

    @Test
    public void testSortedReversed(){
        strategy = new AuthorsBookDtoOrderStategy();
        List<BookDto>res = strategy.execute(books, true);
        assertEquals(books.size(), res.size());
        assertEquals(books.get(0), res.get(3));
        assertEquals(books.get(1), res.get(2));
        assertEquals(books.get(2), res.get(0));
        assertEquals(books.get(3), res.get(1));
    }
}
package com.sonodavide.test.gestorelibreria.service.BookDtoOrderStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;
import com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy.IsbnBookDtoOrderStrategy;
import com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy.TitleBookDtoOrderStrategy;
import com.sonodavide.test.gestorelibreria.InitHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TitleBookDtoOrderStrategyTest {
    TitleBookDtoOrderStrategy strategy;
    private List<BookDto> books;
    @BeforeEach
    public void setup(){
        books = InitHelper.initBooksExample();
    }

    @Test
    public void testSorting() {
        strategy = new TitleBookDtoOrderStrategy();
        List<BookDto>res = strategy.execute(books, false);
        System.out.println(res);
        assertEquals(books.size(), res.size());
        assertEquals(books.get(1), res.get(2));
        assertEquals(books.get(2), res.get(0));
        assertEquals(books.get(0), res.get(1));
    }

    @Test
    public void testReverseSorting() {
        strategy = new TitleBookDtoOrderStrategy();
        List<BookDto>res = strategy.execute(books, false);
        assertEquals(books.size(), res.size());
        assertEquals(books.get(2), res.get(0));
        assertEquals(books.get(1), res.get(1));
        assertEquals(books.get(0), res.get(2));
    }

}

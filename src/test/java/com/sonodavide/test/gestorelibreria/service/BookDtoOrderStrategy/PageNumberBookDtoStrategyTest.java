package com.sonodavide.test.gestorelibreria.service.BookDtoOrderStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;
import com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy.PageNumberBookDtoStrategy;
import com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy.TitleBookDtoOrderStrategy;
import com.sonodavide.test.gestorelibreria.InitHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class PageNumberBookDtoStrategyTest {
    private PageNumberBookDtoStrategy strategy;
    private List<BookDto> books;

    @BeforeEach
    public void setup() {
        books = InitHelper.initBooksExample();
        strategy = new PageNumberBookDtoStrategy();
    }

    @Test
    public void testSorting() {
        List<BookDto> res = strategy.execute(books, false);
        assertEquals(books.size(), res.size());
        assertEquals(books.get(1), res.get(0)); // 1984 (328)
        assertEquals(books.get(3), res.get(1)); // Il nome della rosa (512) con 2 autori
        assertEquals(books.get(2), res.get(2)); // Il nome della rosa (512)
        assertEquals(books.get(5), res.get(3)); // Sapiens (528)
        assertEquals(books.get(0), res.get(4)); // Il Signore degli Anelli (1216)
        assertEquals(books.get(4), res.get(5)); // Il Signore degli Anelli edizione 2 (1220)
    }

    @Test
    public void testReverseSorting() {
        List<BookDto> res = strategy.execute(books, true);
        assertEquals(books.size(), res.size());
        assertEquals(books.get(4), res.get(0)); // 1220
        assertEquals(books.get(0), res.get(1)); // 1216
        assertEquals(books.get(5), res.get(2)); // 528
        assertEquals(books.get(3), res.get(3)); // 512 (2 autori)
        assertEquals(books.get(2), res.get(4)); // 512
        assertEquals(books.get(1), res.get(5)); // 328
    }
}
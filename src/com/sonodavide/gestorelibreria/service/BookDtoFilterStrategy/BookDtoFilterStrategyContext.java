package com.sonodavide.gestorelibreria.service.BookDtoFilterStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class BookDtoFilterStrategyContext {

    private BookDtoFilterStrategy filter;

    public void setFilter(BookDtoFilterStrategy filter) {
        this.filter = filter;
    }
    public List<BookDto> execute(List<BookDto> books, String param) {
        return filter.execute(books, param);
    }
}

package com.sonodavide.gestorelibreria.service.BookDtoFilterStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


public class BookDtoFilterStrategyContext {
    @Getter
    private BookDtoFilterStrategy strategy;

    public BookDtoFilterStrategyContext(){
        this.strategy = new TitleBookDtoFilterStrategy();
    }

    public List<BookDto> execute(List<BookDto> books, String param) {
        return strategy.execute(books, param);
    }
}

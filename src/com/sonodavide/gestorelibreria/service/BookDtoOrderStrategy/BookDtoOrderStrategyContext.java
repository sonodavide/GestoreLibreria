package com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;
import com.sonodavide.gestorelibreria.service.BookDtoFilterStrategy.BookDtoFilterStrategy;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class BookDtoOrderStrategyContext {
    @Setter
    private BookDtoOrderStrategy strategy;

    public BookDtoOrderStrategyContext(){
        strategy = new TitleBookDtoOrderStrategy();
    }
    public List<BookDto> execute(List<BookDto> books, boolean reverse) {

        return strategy.execute(books, reverse);
    }
}

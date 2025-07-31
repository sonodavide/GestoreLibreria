package com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class ReadStatusBookDtoOrderStrategy implements BookDtoOrderStrategy {
    public ReadStatusBookDtoOrderStrategy(){
    }
    @Override
    public List<BookDto> execute(List<BookDto> bookDtos, boolean reverse) {
        Comparator<BookDto> comparator = Comparator.comparing(book -> book.getReadStatus().getVal(), String.CASE_INSENSITIVE_ORDER);
        if(reverse){
            comparator = comparator.reversed();
        }
        return bookDtos.stream()
                .sorted(comparator)
                .collect(toList());
    }
}

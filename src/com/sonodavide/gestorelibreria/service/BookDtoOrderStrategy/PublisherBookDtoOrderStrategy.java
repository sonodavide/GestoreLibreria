package com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PublisherBookDtoOrderStrategy implements BookDtoOrderStrategy {
    @Override
    public List<BookDto> execute(List<BookDto> bookDtos, boolean reverse) {
        Comparator<BookDto> comparator = Comparator.comparing(BookDto::getPublisher, String.CASE_INSENSITIVE_ORDER);
        if (reverse){
            comparator = comparator.reversed();
        }
        return bookDtos.stream().sorted(comparator).collect(Collectors.toList());
    }
}

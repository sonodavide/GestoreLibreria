package com.sonodavide.gestorelibreria.service.BookDtoFilterStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;

import java.util.List;
import java.util.stream.Collectors;

public class GenresBookDtoFilterStrategy implements BookDtoFilterStrategy {
    @Override
    public List<BookDto> execute(List<BookDto> bookDtos, String param) {
        String paramsLC = param.toLowerCase();
        return bookDtos.stream()
                .filter(bookDto -> bookDto.getGenres().stream()
                        .anyMatch(genre -> genre.toLowerCase().startsWith(paramsLC))
                ).collect(Collectors.toList());
    }
}

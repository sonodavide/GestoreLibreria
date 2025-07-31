package com.sonodavide.gestorelibreria.service.BookDtoFilterStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorsBookDtoFilterStrategy implements BookDtoFilterStrategy {
    @Override
    public List<BookDto> execute(List<BookDto> bookDtos, String param) {
        String paramLC = param.toLowerCase();
        return bookDtos.stream().filter(
                bookDto -> bookDto.getAuthors().stream().anyMatch(author -> author.startsWith(paramLC))
        ).collect(Collectors.toList());
    }
}

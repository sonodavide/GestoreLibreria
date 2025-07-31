package com.sonodavide.gestorelibreria.service.BookDtoFilterStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;

import java.util.List;

public interface BookDtoFilterStrategy {
    List<BookDto> execute(List<BookDto> bookDtos, String param);
}

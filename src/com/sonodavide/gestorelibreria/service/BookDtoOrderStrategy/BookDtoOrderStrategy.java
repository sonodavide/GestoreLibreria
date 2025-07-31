package com.sonodavide.gestorelibreria.service.BookDtoOrderStrategy;

import com.sonodavide.gestorelibreria.model.BookDto;

import java.util.List;

public interface BookDtoOrderStrategy {
    List<BookDto> execute(List<BookDto> bookDtos, boolean reverse);
}

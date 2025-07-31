package com.sonodavide.gestorelibreria.mapper;

import com.sonodavide.gestorelibreria.model.Book;
import com.sonodavide.gestorelibreria.model.BookDto;

public class BookDtoMapper {
    public static BookDto toBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setIsbn(book.getIsbn());
        bookDto.setTitle(book.getTitle());
        bookDto.setPages(book.getPages());
        return bookDto;
    }

    public static Book fromBookDto(BookDto bookDto) {
        return new Book.Builder()
                .setIsbn(bookDto.getIsbn())
                .setPages(bookDto.getPages())
                .setTitle(bookDto.getTitle())
                .build();
    }
}

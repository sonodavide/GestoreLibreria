package com.sonodavide.gestorelibreria.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sonodavide.gestorelibreria.model.Book;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class JsonBookRepository implements BookRepository {

    private final ObjectMapper mapper;
    private final Path filePath;

    public JsonBookRepository() {
        this(Paths.get("books.json")); // Default file path
    }

    public JsonBookRepository(Path filePath) {
        this.mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        this.filePath = filePath;

        // Create the file if it doesn't exist
        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.writeString(filePath, "[]", StandardOpenOption.CREATE);
            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize JSON file", e);
            }
        }
    }

    @Override
    public synchronized Book findByIsbn(String isbn) {
        return readAll().stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    @Override
    public synchronized List<Book> findAll() {
        return readAll();
    }

    @Override
    public synchronized Book add(Book book) {
        List<Book> books = readAll();

        if (books.stream().anyMatch(b -> b.getIsbn().equals(book.getIsbn()))) {
            throw new IllegalArgumentException("A book with this ISBN already exists.");
        }

        books.add(book);
        writeAll(books);
        return book;
    }

    @Override
    public synchronized Book update(Book book) {
        List<Book> books = readAll();

        boolean exists = books.stream().anyMatch(b -> b.getIsbn().equals(book.getIsbn()));
        if (!exists) {
            throw new NoSuchElementException("No book found with ISBN: " + book.getIsbn());
        }

        List<Book> updated = books.stream()
                .map(b -> b.getIsbn().equals(book.getIsbn()) ? book : b)
                .collect(Collectors.toList());

        writeAll(updated);
        return book;
    }

    @Override
    public synchronized Book delete(Book book) {
        List<Book> books = readAll();
        boolean removed = books.removeIf(b -> b.getIsbn().equals(book.getIsbn()));

        if (!removed) return null;

        writeAll(books);
        return book;
    }

    private List<Book> readAll() {
        try {
            if (Files.size(filePath) == 0) {
                return new ArrayList<>();
            }
            return mapper.readValue(Files.readAllBytes(filePath), new TypeReference<List<Book>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }

    private void writeAll(List<Book> books) {
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("books_temp_", ".json");


            mapper.writeValue(tempFile.toFile(), books);


            try {
                mapper.readValue(tempFile.toFile(), new TypeReference<List<Book>>() {});
            } catch (IOException validationError) {
                throw new RuntimeException("The JSON file written is invalid. Aborting write.", validationError);
            }


            Files.move(tempFile, filePath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            try {
                mapper.readValue(filePath.toFile(), new TypeReference<List<Book>>() {});
            } catch (IOException validationError) {
                throw new RuntimeException("The JSON file written is invalid. Aborting write.", validationError);
            }
        } catch (IOException e) {
            if (tempFile != null) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException ignored) {}
            }
            throw new RuntimeException("Error during safe JSON file write", e);
        }
    }
    @Override
    public synchronized List<Book> addAll(List<Book> newBooks) {
        List<Book> existingBooks = readAll();

        // Filter out books with duplicate ISBNs
        Set<String> existingIsbns = existingBooks.stream()
                .map(Book::getIsbn)
                .collect(Collectors.toSet());

        List<Book> booksToAdd = newBooks.stream()
                .filter(book -> !existingIsbns.contains(book.getIsbn()))
                .collect(Collectors.toList());

        if (booksToAdd.isEmpty()) {
            return Collections.emptyList();
        }

        existingBooks.addAll(booksToAdd);
        writeAll(existingBooks);
        return booksToAdd;
    }
}

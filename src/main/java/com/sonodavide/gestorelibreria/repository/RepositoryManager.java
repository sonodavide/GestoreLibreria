package com.sonodavide.gestorelibreria.repository;

public enum RepositoryManager {
    INSTANCE;

    private static BookRepository bookRepository = null;
    public static synchronized BookRepository getInstance(){
        if(bookRepository == null){
            bookRepository = new JsonBookRepository();
        }
        return bookRepository;

        
    }
}

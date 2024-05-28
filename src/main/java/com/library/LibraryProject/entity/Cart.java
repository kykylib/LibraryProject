package com.library.LibraryProject.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Book> books;

    public Cart() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        for (int i = 0; i<books.size();i++){
            if(books.get(i).getId().equals(book.getId())){
                books.remove(i);
            }
        }
    }

    public void clear() {
        books.clear();
    }

}



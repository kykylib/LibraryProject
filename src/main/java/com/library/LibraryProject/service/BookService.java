package com.library.LibraryProject.service;

import com.library.LibraryProject.entity.Book;
import com.library.LibraryProject.entity.User;
import com.library.LibraryProject.repository.BookRepository;
import com.library.LibraryProject.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    public List<Book> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContaining(author);
    }

    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.isAvailable()) {
            bookRepository.deleteById(id);
        }
    }
}






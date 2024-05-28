package com.library.LibraryProject;

import com.library.LibraryProject.entity.Book;
import com.library.LibraryProject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private BookService bookService;

    @Override
    public void run(String... args) throws Exception {
//        bookService.save(new Book(null, "Effective Java", "Joshua Bloch", "Programming", true));
//        bookService.save(new Book(null, "Clean Code", "Robert C. Martin", "Programming", true));
//        bookService.save(new Book(null, "The Pragmatic Programmer", "Andrew Hunt", "Programming", true));
//        bookService.save(new Book(null, "Design Patterns", "Erich Gamma", "Programming", true));
//        bookService.save(new Book(null, "Refactoring", "Martin Fowler", "Programming", true));
//        bookService.save(new Book(null, "Introduction to Algorithms", "Thomas H. Cormen", "Algorithms", true));
//        bookService.save(new Book(null, "Algorithms Unlocked", "Thomas H. Cormen", "Algorithms", true));
//        bookService.save(new Book(null, "Cracking the Coding Interview", "Gayle Laakmann McDowell", "Interview", true));
//        bookService.save(new Book(null, "Head First Design Patterns", "Eric Freeman", "Design Patterns", true));
//        bookService.save(new Book(null, "Java Concurrency in Practice", "Brian Goetz", "Programming", true));
//

    }
}



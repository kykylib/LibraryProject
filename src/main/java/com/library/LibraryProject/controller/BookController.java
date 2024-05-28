package com.library.LibraryProject.controller;

import com.library.LibraryProject.entity.Book;
import com.library.LibraryProject.entity.Loan;
import com.library.LibraryProject.entity.User;
import com.library.LibraryProject.service.BookService;
import com.library.LibraryProject.service.LoanService;
import com.library.LibraryProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoanService loanService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public String listBooks(@RequestParam(value = "category", required = false) String category,
                            @RequestParam(value = "search", required = false) String search,
                            @RequestParam(value = "filterBy", required = false) String filterBy,
                            Model model, Authentication authentication) {
        List<Book> books;

        if (search != null) {
            if ("title".equalsIgnoreCase(filterBy)) {
                books = bookService.searchBooksByTitle(search);
            } else if ("author".equalsIgnoreCase(filterBy)) {
                books = bookService.searchBooksByAuthor(search);
                System.out.println(books);
            } else {
                books = bookService.findAllBooks();
                System.out.println(books);
            }
        } else if (category != null) {
            books = bookService.findBooksByCategory(category);
            System.out.println(books);
        } else {
            books = bookService.findAllBooks();
            System.out.println(books);
        }
        model.addAttribute("books", books);
        return "catalog";
    }

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/catalog";
    }

}




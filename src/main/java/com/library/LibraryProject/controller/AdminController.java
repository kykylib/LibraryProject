package com.library.LibraryProject.controller;

import com.library.LibraryProject.entity.Book;
import com.library.LibraryProject.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @GetMapping("/add-book")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add-book")
    public String addBook(@ModelAttribute("book") Book book) {
        book.setAvailable(true);
        bookService.save(book);
        return "redirect:/catalog";
    }

    @PostMapping("/delete-book")
    public String deleteBook(@RequestParam("bookId") Long bookId) {
        bookService.deleteById(bookId);
        return "redirect:/catalog";
    }
}


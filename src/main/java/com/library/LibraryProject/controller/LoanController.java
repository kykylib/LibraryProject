package com.library.LibraryProject.controller;

import com.library.LibraryProject.entity.Book;
import com.library.LibraryProject.entity.Loan;
import com.library.LibraryProject.entity.User;
import com.library.LibraryProject.entity.Cart;
import com.library.LibraryProject.service.BookService;
import com.library.LibraryProject.service.LoanService;
import com.library.LibraryProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoanController {
    @Autowired
    private LoanService loanService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private Cart cart;

    @PostMapping("/return")
    public String returnBook(@RequestParam Long bookId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        Book book = bookService.getBookById(bookId);
        loanService.returnBook(user, book);
        return "redirect:/catalog";
    }

    @PostMapping("/cart/confirm")
    public String confirmLoan(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        for (Book book : cart.getBooks()) {
            loanService.loanBook(user, book);
        }
        cart.clear();
        return "redirect:/catalog";
    }
}







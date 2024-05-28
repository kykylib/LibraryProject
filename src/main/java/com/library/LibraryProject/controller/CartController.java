package com.library.LibraryProject.controller;

import com.library.LibraryProject.entity.Book;
import com.library.LibraryProject.entity.Cart;
import com.library.LibraryProject.entity.User;
import com.library.LibraryProject.service.BookService;
import com.library.LibraryProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private Cart cart;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam Long bookId, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            Book book = bookService.getBookById(bookId);
            if (book != null) {
                cart.removeBook(book);
            } else {
            }
        }
        return "redirect:/catalog";
    }
    @GetMapping("/cart/show")
    public String showCart(@RequestParam Long bookId, Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            List<Book> books = cart.getBooks();
            model.addAttribute("cart",books);
        }
        return "redirect:/catalog";
    }


    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long bookId, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.findByUsername(userDetails.getUsername());
            if (user != null) {
                Book book = bookService.getBookById(bookId);
                cart.addBook(book);
            }
        }
        return "redirect:/catalog";
    }
}

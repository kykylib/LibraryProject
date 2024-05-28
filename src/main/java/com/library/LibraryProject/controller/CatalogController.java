package com.library.LibraryProject.controller;

import com.library.LibraryProject.entity.Book;
import com.library.LibraryProject.entity.Cart;
import com.library.LibraryProject.entity.Loan;
import com.library.LibraryProject.entity.User;
import com.library.LibraryProject.service.BookService;
import com.library.LibraryProject.service.CategoryService;
import com.library.LibraryProject.service.LoanService;
import com.library.LibraryProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CatalogController {

    private final BookService bookService;
    private final CategoryService categoryService;
    private final Cart cart;

    @Autowired
    private UserService userService;

    @Autowired
    private LoanService loanService;

    @Autowired
    public CatalogController(BookService bookService, CategoryService categoryService, Cart cart) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.cart = cart;
    }

    @GetMapping("/catalog")
    public String showCatalog(Authentication authentication,Model model) {
        List<Book> books = bookService.findAllBooks();

        model.addAttribute("books", books);
        model.addAttribute("cart", cart);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        List<Loan> borrowedLoans = loanService.findByUser(user);
        List<Book> borrowedBooks = new ArrayList<>();
        for (int i = 0; i < borrowedLoans.size(); i++) {
            if(borrowedLoans.get(i).getReturnDate() == null){
                borrowedBooks.add(borrowedLoans.get(i).getBook());
            }
        }
        model.addAttribute("borrowedBooks", borrowedBooks);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);

        System.out.println("Admin " + isAdmin);

        return "catalog";
    }
}




package com.library.LibraryProject.service;

import com.library.LibraryProject.entity.Book;
import com.library.LibraryProject.entity.Loan;
import com.library.LibraryProject.entity.User;
import com.library.LibraryProject.repository.BookRepository;
import com.library.LibraryProject.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BookRepository bookRepository;

    public Loan loanBook(User user, Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            Loan loan = new Loan();
            loan.setBook(book);
            loan.setUser(user);
            loan.setLoanDate(LocalDate.now());
            bookRepository.save(book);
            return loanRepository.save(loan);
        }
        return null;
    }

    public Loan returnBook(User user, Book book) {
        List<Loan> loans = loanRepository.findByUserAndBookAndReturnDateIsNull(user, book);
        if (!loans.isEmpty()) {
            Loan loan = loans.get(0);
            book.setAvailable(true);
            bookRepository.save(book);
            loan.setReturnDate(LocalDate.now());
            return loanRepository.save(loan);
        }
        return null;
    }

    public List<Loan> findByUser(User user) {
        return loanRepository.findByUser(user);
    }


}



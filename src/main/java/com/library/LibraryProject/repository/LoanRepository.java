package com.library.LibraryProject.repository;

import com.library.LibraryProject.entity.Loan;
import com.library.LibraryProject.entity.Book;
import com.library.LibraryProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserAndBookAndReturnDateIsNull(User user, Book book);
    List<Loan> findByUser(User user);
}




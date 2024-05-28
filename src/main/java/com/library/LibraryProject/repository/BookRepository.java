package com.library.LibraryProject.repository;

import com.library.LibraryProject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory(String category);
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContaining(String author);
}




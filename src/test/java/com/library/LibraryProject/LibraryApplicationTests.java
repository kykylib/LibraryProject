package com.library.LibraryProject;

import com.library.LibraryProject.entity.Book;
import com.library.LibraryProject.entity.User;
import com.library.LibraryProject.service.BookService;
import com.library.LibraryProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LibraryApplicationTests {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Author");
//        Book savedBook = bookService.addBook(book);
//        assertNotNull(savedBook.getId());
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
//        User registeredUser = userService.registerNewUser(user);
//        assertNotNull(registeredUser.getId());
    }
}

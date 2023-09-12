package com.libraryapp.springbootlibrary.controller;

import com.libraryapp.springbootlibrary.entity.Book;
import com.libraryapp.springbootlibrary.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     *
     * @return number of books checked out by userId
     */
    @GetMapping("/secure/currentloans/count")
    public int currentLoansCount() {
        String userEmail = "testuser@email.com";
        return bookService.currentLoansCount(userEmail);
    }

    /**
     *
     * @param bookId
     * @return a boolean if a specific book is checked out.
     */
    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId) {
        String userEmail = "testuser@email.com";
        return bookService.checkoutBookByUser(userEmail, bookId);
    }

    /**
     *
     * @param bookId
     * @return checks out a book to a user.
     * @throws Exception
     */
    @PutMapping("/secure/checkout")
    public Book checkoutBook(@RequestParam Long bookId) throws Exception {
        String userEmail = "testuser@email.com";
        return bookService.checkoutBook(userEmail, bookId);
    }
}

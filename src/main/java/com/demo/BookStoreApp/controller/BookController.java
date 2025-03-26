package com.demo.BookStoreApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.BookStoreApp.entities.Book;
import com.demo.BookStoreApp.service.BookService;


@RestController    //making the  class as a RESTful controller

public class BookController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookController.class);


    @Autowired      // Injecting  the BookService bean
    private BookService bookService;
    
    //adding the new book by using method Post
    @RequestMapping(path="/books",method=RequestMethod.POST)
    public Book addBook(@RequestBody Book book) {
    	logger.info("Reequest to add a new book: {}", book);
    	 Book addedBook = bookService.addBook(book);
         logger.info("Book added successfully with ID: {}", addedBook.getId());
         return addedBook;    // Calls the service method to add a book and returns the added book
    }
    
    //display the list of books using the Get method
    @RequestMapping(path="/books",method=RequestMethod.GET)
    public List<Book> getAllBooks() {
    	logger.info("Request to fetch all books");
    	logger.info("Book fetched successfully");
        return bookService.getAllBooks();   // Calls the service method to get all books and returns the list
    }
    
    //fetch the specific book details by book Id using the Get method 
    @RequestMapping(path="/books/{id}",method=RequestMethod.GET)
    public Book getBookById(@PathVariable Long id) {
    	logger.info("Request to fetch book with ID: {}", id);
        Book book = bookService.getBookById(id);
        logger.info("Book fetched successfully: {}", book);
        return book;   // Calls the service method to get a book by its ID and returns the book
    }
    
    //Updating the existing book by Id using the Put method
    @RequestMapping(path="/books/{id}",method=RequestMethod.PUT)
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
    	logger.info("Rquest to update book with ID: {}", id);
    	logger.info("Book updated successfully:");
        return bookService.updateBook(id, book);  // Calls the service method to update the book and returns the updated book
    }

    
    //deleting the book by Id using the Delete method
    @RequestMapping(path="/books/{id}",method=RequestMethod.DELETE)
    public void deleteBook(@PathVariable Long id) {
    	logger.warn("Received request to delete book with ID: {}", id);
        bookService.deleteBook(id);     // Calls the service method to delete the book
        logger.info("Book deleted successfully with ID: {}", id);
    }
    
}

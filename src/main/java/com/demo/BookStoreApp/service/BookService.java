package com.demo.BookStoreApp.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.BookStoreApp.dao.BookRepository;
import com.demo.BookStoreApp.entities.Book;

@Service
public class BookService {
	private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired  // Injects the BookRepository dependency
    private BookRepository bookRepository;

    // Add a new book
    public Book addBook(Book book) {
    	logger.debug("Adding book: {}", book);
        return bookRepository.save(book);  // Saves the book entity
    }

    // Retrieve all books
    public List<Book> getAllBooks() {
    	logger.debug("Fetching all books");
        return bookRepository.findAll();   // Finds and returns a list of all books
    }

    // Retrieve a book by ID
    public Book getBookById(Long id) {
    	logger.debug("Fetching book by ID: {}", id);
    	// Uses findById() to get the book
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
    }

    // Update an existing book
    public Book updateBook(Long id, Book book) {
    	logger.debug("Updating book with ID: {}", id);
    	 // Fetches the existing book by ID
        Book existingBook = getBookById(id);
        // Updates book details
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPrice(book.getPrice());
        existingBook.setPublishedDate(book.getPublishedDate());
        return bookRepository.save(existingBook);   // Saves the updated book entity 
    }

    // Delete a book by ID
    public void deleteBook(Long id) {
    	logger.warn("Deleting book with ID: {}", id);
    	// Deletes the book by ID from the repository
        bookRepository.deleteById(id);
    }
}

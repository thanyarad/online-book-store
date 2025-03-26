package com.demo.BookStoreApp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.BookStoreApp.dao.BookRepository;
import com.demo.BookStoreApp.entities.Book;

@ExtendWith(MockitoExtension.class)  // Enables Mockito for JUnit 5
class BookServiceTest {
	 // Mocking the BookRepository to isolate service layer testing
    @Mock   
    private BookRepository bookRepository;

    @InjectMocks   // Injecting the mocked BookRepository into the BookService
    private BookService bookService;

    @Test  // testing for adding a new book
    void addBookTest() {
        // Create a sample book object
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Harry Potter");
        book.setAuthor("J.K Rowling");
        book.setPrice(new BigDecimal("700.00"));
        book.setPublishedDate(LocalDate.parse("1997-06-26"));

        // Mocking the save method of the repository
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        // Call the service method
        Book result = bookService.addBook(book);

        // Verify the result
        assertNotNull(result);
        assertEquals("Harry Potter", result.getTitle());
        assertEquals("J.K Rowling", result.getAuthor());
        assertEquals(new BigDecimal("700.00"), result.getPrice());
        assertEquals(LocalDate.parse("1997-06-26"), result.getPublishedDate());
    }
    
    //test for retrieving all books
    @Test
    void getAllBooksTest() {
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book(1L, "Harry Potter", "J.K Rowling", new BigDecimal("700.00"), LocalDate.parse("1997-06-26"));
        Book book2 = new Book(2L, "Romeo and Juliet", "William Shakespheare", new BigDecimal("430.00"), LocalDate.parse("2008-10-02"));
        bookList.add(book1);
        bookList.add(book2);
        
        // Mocking the findAll method to return the book list
        when(bookRepository.findAll()).thenReturn(bookList);
        // Calling the getAllBooks method from the service
        List<Book> result = bookService.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Harry Potter", result.get(0).getTitle());
        assertEquals("Romeo and Juliet", result.get(1).getTitle());
    }
    
     // Test for retrieving a book by ID
    @Test
    void getBookByIdTest() {
        Book book = new Book(1L, "Romeo and Juliet", "William Shakespheare", new BigDecimal("430.00"), LocalDate.parse("2008-10-02"));

        // Mocking the findById method to return the sample book
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
   
        // Calling the getBookById method from the service
        Book result = bookService.getBookById(1L);
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Romeo and Juliet", result.getTitle());
        assertEquals("William Shakespheare", result.getAuthor());
    }
    
    // Test for updating a book
    @Test
    void updateBookTest() {
        Book existingBook = new Book(1L, "Romeo and Juliet", "William Shakespheare", new BigDecimal("430.00"), LocalDate.parse("2008-10-02"));
        Book updatedBook = new Book(1L, "Goosebumps", "R.L.Stine", new BigDecimal("800.00"), LocalDate.parse("1992-08-16"));

        // Mocking the findById and save methods of the repository
        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        // Calling the updateBook method from the service
        Book result = bookService.updateBook(1L, updatedBook);

        assertNotNull(result);
        assertEquals("Goosebumps", result.getTitle());
        assertEquals(new BigDecimal("800.00"), result.getPrice());
        assertEquals(LocalDate.parse("1992-08-16"), result.getPublishedDate());
    }
    // Unit test for deleting a book
    @Test
    void deleteBookTest() {
    	// Mocking the deleteById method of the repository
        doNothing().when(bookRepository).deleteById(1L);
        // Calling the deleteBook method from the service
        bookService.deleteBook(1L);
        // Verifying that the deleteById method was called exactly once
        verify(bookRepository, times(1)).deleteById(1L);
    }

}

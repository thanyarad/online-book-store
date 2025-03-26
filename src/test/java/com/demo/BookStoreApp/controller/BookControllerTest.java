package com.demo.BookStoreApp.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.BookStoreApp.entities.Book;
import com.demo.BookStoreApp.service.BookService;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock   // Mocking the BookService dependency
    private BookService bookService;

    @InjectMocks  // Injecting the mocked service into the BookController
    private BookController bookController;
    
   // Sample Book objects used for testing
    private Book book1;
    private Book book2;
    
    //Setup method to initialize mock objects before each test.
    @BeforeEach
    void setup() {
        book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Harry Potter");
        book1.setAuthor("J.K Rowling");
        book1.setPrice(new BigDecimal("700.00"));
        book1.setPublishedDate(LocalDate.parse("1997-06-26"));

        book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Romeo and Juliet");
        book2.setAuthor("William Shakespheare");
        book2.setPrice(new BigDecimal("430.00"));
        book2.setPublishedDate(LocalDate.parse("2008-10-02"));
    }
    
    //Test for adding a new book.
    @Test
    void testAddBook() {
    	// Mocking the addBook method of the service layer
        when(bookService.addBook(book1)).thenReturn(book1);

        Book result = bookController.addBook(book1);

        assertNotNull(result);   // Verifying the result
        assertEquals("Harry Potter", result.getTitle());
        assertEquals("J.K Rowling", result.getAuthor());
    }
    
    //Test for retrieving all books.
    @Test
    void testGetAllBooks() {
    	// Mocking the getAllBooks method
        List<Book> books = Arrays.asList(book1, book2);
        when(bookService.getAllBooks()).thenReturn(books);

        List<Book> result = bookController.getAllBooks();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Harry Potter", result.get(0).getTitle());
        assertEquals("Romeo and Juliet", result.get(1).getTitle());
    }
 
   // Test for retrieving a book by its ID.
    @Test
    void testGetBookById() {
        when(bookService.getBookById(1L)).thenReturn(book1);

        Book result = bookController.getBookById(1L);

        assertNotNull(result);
        assertEquals("Harry Potter", result.getTitle());
        assertEquals("J.K Rowling", result.getAuthor());
    }
    
    //Test for updating an existing book.
    @Test
    void testUpdateBook() {
    	// Mocking the updateBook method
        when(bookService.updateBook(1L, book1)).thenReturn(book1);
  
     // Calling the updateBook method from the controller
        Book result = bookController.updateBook(1L, book1);

        assertNotNull(result);
        assertEquals("Harry Potter", result.getTitle());
        assertEquals("J.K Rowling", result.getAuthor());
    }
    //Test for deleting a book by its ID.
    @Test
    void testDeleteBook() {
    	// Mocking the deleteBook method
        doNothing().when(bookService).deleteBook(1L);

        bookController.deleteBook(1L);
        verify(bookService, times(1)).deleteBook(1L);
    }
}

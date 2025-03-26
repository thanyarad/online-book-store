package com.demo.BookStoreApp.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
// Annotates this class as a JPA entity that will be mapped to a database table
@Entity
public class Book {
	// Primary key field for the Book entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ID will be generated automatically using the identity strategy
    private Long id;

    private String title;  // Title of the book
    private String author;  // Author of the book
    private BigDecimal price;   // Price of the book as a BigDecimal to handle currency values
    private LocalDate publishedDate;  // Publication date of the book
    
    public Book(Long id, String title, String author, BigDecimal price, LocalDate publishedDate) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.publishedDate = publishedDate;
    }
 // Getter and setter for id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public LocalDate getPublishedDate() {
		return publishedDate;
	}
	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", price=" + price + ", publishedDate="
				+ publishedDate + "]";
	}
    
}

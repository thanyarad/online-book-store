package com.demo.BookStoreApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.BookStoreApp.entities.Book;
// BookRepository interface that extends JpaRepository to provide CRUD operations for Book entities
public interface BookRepository extends JpaRepository<Book, Long> {

}

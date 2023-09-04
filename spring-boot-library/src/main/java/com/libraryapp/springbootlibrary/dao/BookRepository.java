package com.libraryapp.springbootlibrary.dao;

import com.libraryapp.springbootlibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}

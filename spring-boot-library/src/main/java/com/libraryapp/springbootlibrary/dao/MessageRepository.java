package com.libraryapp.springbootlibrary.dao;

import com.libraryapp.springbootlibrary.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

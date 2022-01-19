package com.abdullah.keycloak.repositories;

import com.abdullah.keycloak.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

/**
 * User: Syed Abdullah
 * Date: 20-Jan-2022
 * Time: 12:09 AM
 */
public interface BookRepository extends JpaRepository<Book, Long>, RevisionRepository<Book, Long, Long> {
}

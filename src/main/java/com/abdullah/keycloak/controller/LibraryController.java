package com.abdullah.keycloak.controller;

import com.abdullah.keycloak.enums.UserRole;
import com.abdullah.keycloak.model.Book;
import com.abdullah.keycloak.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


/**
 * User: Syed Abdullah
 * Date: 19-Jan-2022
 * Time: 6:58 PM
 */

@RestController
@RequestMapping("api/v1")
public class LibraryController {

    @Autowired
    private BookService bookService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/books")
    public ResponseEntity<?> getBooks(Model model) {
        return ResponseEntity.ok().body(bookService.readAll());
    }

    @RolesAllowed({UserRole.ADMIN})
    @PostMapping(value = "/book")
    public ResponseEntity<?> saveNewBook(@Valid @RequestBody Book book) throws Exception {
        bookService.addNewBook(book);
        return ResponseEntity.ok().body("New book added.");
    }

    @RolesAllowed({UserRole.ADMIN, UserRole.TRAINEE})
    @GetMapping(value = "/book/{id}")
    public ResponseEntity<?> getBook(@PathVariable(value = "id") Long id) throws Exception {
        return ResponseEntity.ok().body(bookService.findBookById(id));
    }

    @RolesAllowed({UserRole.ADMIN})
    @PutMapping(value = "/book/{id}")
    public ResponseEntity<?> updateBook(@Valid @RequestBody Book book, @PathVariable(value = "id") Long id) throws Exception {
        bookService.updateBook(book,id);
        return ResponseEntity.ok().body("Book updated.");
    }

    @RolesAllowed({UserRole.ADMIN})
    @DeleteMapping(value = "/book/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(value = "id") Long id) throws Exception {
        bookService.deleteBook(id);
        return ResponseEntity.ok().body("Book deleted.");
    }

    //todo- Logout not working
    @GetMapping(value = "/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return ResponseEntity.ok().body("Logged out.");
    }
}

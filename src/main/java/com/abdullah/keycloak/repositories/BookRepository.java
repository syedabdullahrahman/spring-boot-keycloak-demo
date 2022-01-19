package com.abdullah.keycloak.repositories;

import com.abdullah.keycloak.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookRepository {
    private static Map<String, Book> books = new ConcurrentHashMap<>();
    private static Integer id = 3;

    static {
        books.put("B01", new Book("B01", "Harry Potter and the Deathly Hallows", "J.K. Rowling"));
        books.put("B02", new Book("B02", "The Lord of the Rings", "J.R.R. Tolkien"));
        books.put("B03", new Book("B03", "War and Peace", "Leo Tolstoy"));

        books.put("A01", new Book("A01", "Harry Potter and the Deathly Hallows", "J.K. Rowling"));
        books.put("A02", new Book("A02", "The Lord of the Rings", "J.R.R. Tolkien"));
        books.put("A03", new Book("A03", "War and Peace", "Leo Tolstoy"));
    }

    public List<Book> readAll() {
        List<Book> allBooks = new ArrayList<>(books.values());
        allBooks.sort(Comparator.comparing(Book::getId));
        return allBooks;
    }

    public void addNewBook(Book book) {
        id++;
        book.setId("B0"+id);
        books.put("B0"+id, book);
    }

    public Book findBookById(String id) throws Exception {
        if(books.containsKey(id)){
            return books.get(id);
        } else {
            throw new Exception("Book not found.");
        }
    }

    public void updateBook(Book book, String id) throws Exception {
        if(books.containsKey(id)){
            books.put(book.getId(), book);
        } else {
            throw new Exception("Update is not possible.");
        }
    }

    public void deleteBook(String id) throws Exception {
        if(books.containsKey(id)){
            books.remove(id);
        } else {
            throw new Exception("Book doesn't exist.");
        }
    }
}

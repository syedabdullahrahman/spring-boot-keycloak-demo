package com.abdullah.keycloak.services;

import com.abdullah.keycloak.enums.Operation;
import com.abdullah.keycloak.model.Book;
import com.abdullah.keycloak.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> readAll() {
        List<Book> allBooks = bookRepository.findAll();
        allBooks.sort(Comparator.comparing(Book::getId));
        return allBooks;
    }

    public Book addNewBook(Book book) throws Exception {
        if(book.getId()==null){
            return bookRepository.save(book);
        } else {
            throw new Exception("Book id exists.");
        }
    }

    public Book findBookById(Long id) throws Exception {
        Optional<Book> book =  bookRepository.findById(id);
        if(book.isPresent()){
            return book.get();
        } else {
            throw new Exception("Book not found.");
        }
    }

    public Book updateBook(Book updatedBook, Long id) throws Exception {
        Optional<Book> currentBook =  bookRepository.findById(id);
        if(currentBook.isPresent()){
            BeanUtils.copyProperties(updatedBook,currentBook.get(),Book.getIgnorablePropertiesAtBeanCopy(Operation.UPDATE).toArray(new String[0]));
            return bookRepository.saveAndFlush(currentBook.get());
        } else {
            throw new Exception("Book not found.");
        }
    }

    public void deleteBook(Long id) throws Exception {
        Optional<Book> currentBook =  bookRepository.findById(id);
        if(currentBook.isPresent()){
            bookRepository.deleteById(id);
        } else {
            throw new Exception("Book doesn't exist.");
        }
    }
}

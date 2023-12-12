package mk.ukim.finki.wp.labofficial.service;

import mk.ukim.finki.wp.labofficial.model.Author;
import mk.ukim.finki.wp.labofficial.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();
    Author addAuthorToBook (Long authorId, String isbn);
    Optional<Book> findBookByIsbn(String isbn);
    List<Book> searchBook(String text);
    void deleteById(Long id);
    Optional<Book> findBookById(Long id);
    Book saveOrUpdate(String isbn, String title, String genre, int year, Long bookstoreId);
}

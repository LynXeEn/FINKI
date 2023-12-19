package mk.ukim.finki.wp.labofficial.service.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.wp.labofficial.model.Author;
import mk.ukim.finki.wp.labofficial.model.Book;
import mk.ukim.finki.wp.labofficial.model.BookStore;
import mk.ukim.finki.wp.labofficial.model.exceptions.AuthorNotFoundException;
import mk.ukim.finki.wp.labofficial.model.exceptions.BookNotFoundException;
import mk.ukim.finki.wp.labofficial.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.labofficial.repository.jpa.BookRepository;
import mk.ukim.finki.wp.labofficial.repository.jpa.BookStoreRepository;
import mk.ukim.finki.wp.labofficial.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookStoreRepository bookStoreRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }


    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, Long bookId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = bookRepository.findBookById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        bookRepository.addAuthorToBook(book, author);

        return author;
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> searchBook(String text) {
        return bookRepository.searchBooksByTitleLikeIgnoreCase(text);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findBookById(id);
    }

    @Override
    @Transactional
    public Book saveOrUpdate(String isbn, String title, String genre, int year, Long bookstoreId) {
        BookStore bookStore = bookStoreRepository.findById(bookstoreId).orElseThrow();
        bookRepository.deleteByIsbn(isbn);
        return bookRepository.save(new Book(isbn, title, genre, year, bookStore));
    }
}

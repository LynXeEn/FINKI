package mk.ukim.finki.wp.labofficial.service.impl;

import mk.ukim.finki.wp.labofficial.model.Author;
import mk.ukim.finki.wp.labofficial.model.Book;
import mk.ukim.finki.wp.labofficial.model.BookStore;
import mk.ukim.finki.wp.labofficial.repository.InMemoryAuthorRepository;
import mk.ukim.finki.wp.labofficial.repository.InMemoryBookRepository;
import mk.ukim.finki.wp.labofficial.repository.InMemoryBookStoreRepository;
import mk.ukim.finki.wp.labofficial.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final InMemoryBookRepository bookRepository;
    private final InMemoryAuthorRepository authorRepository;
    private final InMemoryBookStoreRepository bookStoreRepository;

    public BookServiceImpl(InMemoryBookRepository bookRepository, InMemoryAuthorRepository authorRepository, InMemoryBookStoreRepository bookStoreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        Author author = authorRepository.findById(authorId).get();
        Book book = bookRepository.findByIsbn(isbn).get();

        book.getAuthors().removeIf(author1 -> author1.getId() == authorId);

        bookRepository.addAuthorToBook(author, book);

        return author;
    }

    @Override
    public Optional<Book> findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> searchBook(String text) {
        return bookRepository.searchBook(text);
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
    public Book saveOrUpdate(String isbn, String title, String genre, int year, Long bookstoreId) {
        BookStore bookStore = bookStoreRepository.findById(bookstoreId).orElseThrow();
        return bookRepository.saveOrUpdate(isbn, title, genre, year, bookStore);
    }
}

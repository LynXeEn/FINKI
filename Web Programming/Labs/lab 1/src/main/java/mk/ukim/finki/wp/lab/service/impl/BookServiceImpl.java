package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.InMemoryAuthorRepository;
import mk.ukim.finki.wp.lab.repository.InMemoryBookRepository;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final InMemoryAuthorRepository authorRepository;
    private final InMemoryBookRepository bookRepository;

    public BookServiceImpl(InMemoryAuthorRepository authorRepository, InMemoryBookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Author addAuthorToBook(Long authorId, String isbn) {
        return bookRepository.addAuthorToBook(authorRepository.findById(authorId), bookRepository.findByIsbn(isbn));
    }

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> findBySearch(String searchText) {
        return bookRepository.searchByText(searchText);
    }
}

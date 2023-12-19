package mk.ukim.finki.wp.labofficial.repository.impl;

import mk.ukim.finki.wp.labofficial.bootstrap.DataHolder;
import mk.ukim.finki.wp.labofficial.model.Author;
import mk.ukim.finki.wp.labofficial.model.Book;
import mk.ukim.finki.wp.labofficial.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository {

    public List<Book> findAll() {
        return DataHolder.books;
    }

    public Optional<Book> findByIsbn(String isbn){
        return DataHolder.books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst();
    }

    public List<Book> searchBook(String text){
        return DataHolder.books.stream().filter(book -> book.getTitle().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }

    public Author addAuthorToBook(Author author, Book book){
        Book bookOriginal = DataHolder.books.stream().filter(book1 -> book1.getIsbn().equals(book.getIsbn())).findFirst().orElseThrow();
        bookOriginal.getAuthors().add(author);

        return author;
    }

    public void deleteById(Long id){
        DataHolder.books.removeIf(book -> book.getId().equals(id));
    }

    public Optional<Book> findBookById(Long id){
        return DataHolder.books.stream().filter(book -> book.getId().equals(id)).findFirst();
    }

    public Book saveOrUpdate(String isbn, String title, String genre, int year, BookStore bookStore){
        DataHolder.books.removeIf(book -> book.getTitle().equals(title));
        Book book = new Book(isbn, title, genre, year, bookStore);
        DataHolder.books.add(book);

        return book;
    }

}

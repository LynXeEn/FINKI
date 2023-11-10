package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.exception.InvalidIsbnBookException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBookRepository {
    public List<Book> findAll(){
        return DataHolder.books;
    }

    public Book findByIsbn(String isbn){
        return DataHolder.books.stream().filter(book -> book.getIsbn().equals(isbn)).findFirst().get();
    }

    public Author addAuthorToBook(Author author, Book book){
        book.getAuthors().removeIf(author1 -> author1.getId() == author.getId());
        book.getAuthors().add(author);
        return author;
    }

    public List<Book> searchByText(String searchText){
        return DataHolder.books.stream().filter(book -> book.getTitle().toLowerCase().contains(searchText.toLowerCase())).collect(Collectors.toList());
    }
}

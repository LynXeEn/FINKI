package mk.ukim.finki.wp.labofficial.repository.jpa;

import mk.ukim.finki.wp.labofficial.model.Author;
import mk.ukim.finki.wp.labofficial.model.Book;
import mk.ukim.finki.wp.labofficial.model.exceptions.BookNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    Optional<Book> findBookById(Long id);

    List<Book> searchBooksByTitleLikeIgnoreCase(String text);

    default Book addAuthorToBook(Book book, Author author) {
        Book bookOriginal = findBookById(book.getId()).orElseThrow(() -> new BookNotFoundException(book.getId()));

        bookOriginal.getAuthors().removeIf(author1 -> author1.getId() == author.getId());
        bookOriginal.getAuthors().add(author);
        return save(bookOriginal);

    }

    void deleteByIsbn(String isbn);
}

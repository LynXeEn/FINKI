package mk.ukim.finki.wp.labofficial.service;

import mk.ukim.finki.wp.labofficial.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> listAuthors();
    Optional<Author> findById(Long id);
}

package mk.ukim.finki.wp.labofficial.service.impl;

import mk.ukim.finki.wp.labofficial.model.Author;
import mk.ukim.finki.wp.labofficial.repository.InMemoryAuthorRepository;
import mk.ukim.finki.wp.labofficial.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final InMemoryAuthorRepository authorRepository;

    public AuthorServiceImpl(InMemoryAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }
}

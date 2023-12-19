package mk.ukim.finki.wp.labofficial.repository.impl;

import mk.ukim.finki.wp.labofficial.bootstrap.DataHolder;
import mk.ukim.finki.wp.labofficial.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository {
    public List<Author> findAll(){
        return DataHolder.authors;
    }

    public Optional<Author> findById(Long id){
        return DataHolder.authors.stream().filter(author -> author.getId() == id).findFirst();
    }
}

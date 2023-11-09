package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryAuthorRepository {
    public List<Author> findAll(){
        return DataHolder.authorList;
    }

    public Author findById(Long id){
        return DataHolder.authorList.stream().filter(author -> author.getId() == id).findFirst().get();
    }
}

package mk.ukim.finki.wp.labofficial.service;

import mk.ukim.finki.wp.labofficial.model.BookStore;

import java.util.List;
import java.util.Optional;

public interface BookStoreService {

    List<BookStore> findAll();
    Optional<BookStore> findById(Long id);

}

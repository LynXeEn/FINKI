package mk.ukim.finki.wp.labofficial.service.impl;

import mk.ukim.finki.wp.labofficial.model.BookStore;
import mk.ukim.finki.wp.labofficial.repository.impl.InMemoryBookStoreRepository;
import mk.ukim.finki.wp.labofficial.repository.jpa.BookStoreRepository;
import mk.ukim.finki.wp.labofficial.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public List<BookStore> findAll() {
        return bookStoreRepository.findAll();
    }

    @Override
    public Optional<BookStore> findById(Long id) {
        return bookStoreRepository.findById(id);
    }
}

package mk.ukim.finki.wp.labofficial.service.impl;

import mk.ukim.finki.wp.labofficial.model.BookStore;
import mk.ukim.finki.wp.labofficial.repository.InMemoryBookStoreRepository;
import mk.ukim.finki.wp.labofficial.service.BookStoreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    private final InMemoryBookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(InMemoryBookStoreRepository bookStoreRepository) {
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

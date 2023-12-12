package mk.ukim.finki.wp.labofficial.repository;

import mk.ukim.finki.wp.labofficial.bootstrap.DataHolder;
import mk.ukim.finki.wp.labofficial.model.BookStore;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookStoreRepository {
    public List<BookStore> findAll(){
        return DataHolder.bookStores;
    }

    public Optional<BookStore> findById(Long id){
        return DataHolder.bookStores.stream().filter(bookStore -> bookStore.getId().equals(id)).findFirst();
    }
}

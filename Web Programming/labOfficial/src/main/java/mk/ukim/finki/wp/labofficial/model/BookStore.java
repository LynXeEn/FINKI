package mk.ukim.finki.wp.labofficial.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String city;
    private String address;
    @OneToMany
    private List<Book> books;

    public BookStore(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.id = (long)(Math.random() * 1000);
        books = new ArrayList<>();
    }

    public BookStore() {

    }
}

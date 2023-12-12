package mk.ukim.finki.wp.labofficial.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookStore {
    private Long id;
    private String name;
    private String city;
    private String address;
    private List<Book> books;

    public BookStore(String name, String city, String address) {
        this.name = name;
        this.city = city;
        this.address = address;
        this.id = (long)(Math.random() * 1000);
        books = new ArrayList<>();
    }
}

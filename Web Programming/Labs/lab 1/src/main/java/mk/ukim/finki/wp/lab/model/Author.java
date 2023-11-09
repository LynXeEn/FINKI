package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data
public class Author {
    private long Id;
    private String name;
    private String surname;
    private String biography;

    public Author(long id, String name, String surname, String biography) {
        Id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }
}

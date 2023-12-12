package mk.ukim.finki.wp.labofficial.model;

import lombok.Data;

@Data
public class Author {
    private long id;
    private String name;
    private String surName;
    private String biography;


    public Author(long id, String name, String surName, String biography) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.biography = biography;
    }
}

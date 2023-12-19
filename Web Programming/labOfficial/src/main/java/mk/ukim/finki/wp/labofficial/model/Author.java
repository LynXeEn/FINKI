package mk.ukim.finki.wp.labofficial.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surName;
    @Column(length = 1000)
    private String biography;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;


    public Author(String name, String surName, String biography, LocalDate dateOfBirth) {
        this.name = name;
        this.surName = surName;
        this.biography = biography;
        this.dateOfBirth = dateOfBirth;
    }

    public Author() {

    }
}

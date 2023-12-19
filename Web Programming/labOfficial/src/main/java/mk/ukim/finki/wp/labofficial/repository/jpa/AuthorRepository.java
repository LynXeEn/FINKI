package mk.ukim.finki.wp.labofficial.repository.jpa;

import mk.ukim.finki.wp.labofficial.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

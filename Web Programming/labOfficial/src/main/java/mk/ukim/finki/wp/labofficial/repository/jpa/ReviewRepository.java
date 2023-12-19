package mk.ukim.finki.wp.labofficial.repository.jpa;

import mk.ukim.finki.wp.labofficial.model.Book;
import mk.ukim.finki.wp.labofficial.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findReviewsByBook(Book book);
    List<Review> findReviewsByTimestampBetween(LocalDateTime date1, LocalDateTime date2);

}

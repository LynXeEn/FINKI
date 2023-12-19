package mk.ukim.finki.wp.labofficial.service;

import mk.ukim.finki.wp.labofficial.model.Book;
import mk.ukim.finki.wp.labofficial.model.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ReviewService {
    List<Review> findReviewsByBook(Long bookId);
    Review addReviewToBook(Long bookId, Integer score, String description, LocalDateTime timestamp);
    List<Review> reviewsBetweenTimestamp(LocalDateTime date1, LocalDateTime date2);
    List<Review> findAllReviews();
    Map<Book, Double> findBookWithTheBestAverageScore();
}

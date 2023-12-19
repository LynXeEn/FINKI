package mk.ukim.finki.wp.labofficial.service.impl;

import mk.ukim.finki.wp.labofficial.model.Book;
import mk.ukim.finki.wp.labofficial.model.Review;
import mk.ukim.finki.wp.labofficial.model.exceptions.BookNotFoundException;
import mk.ukim.finki.wp.labofficial.repository.jpa.BookRepository;
import mk.ukim.finki.wp.labofficial.repository.jpa.ReviewRepository;
import mk.ukim.finki.wp.labofficial.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Review> findReviewsByBook(Long bookId) {
        Book book = bookRepository.findBookById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        return reviewRepository.findReviewsByBook(book);
    }

    @Override
    public Review addReviewToBook(Long bookId, Integer score, String description, LocalDateTime timestamp) {
        //TODO check this method one more time
        Book book = bookRepository.findBookById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        return reviewRepository.save(new Review(score, description, book, timestamp));
    }

    @Override
    public List<Review> reviewsBetweenTimestamp(LocalDateTime date1, LocalDateTime date2) {
        return reviewRepository.findReviewsByTimestampBetween(date1, date2);
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Map<Book, Double> findBookWithTheBestAverageScore() {
        Map<Book,Double> finalMap = new HashMap<>();
        double max = -1.0;
        Book bookForReturn = null;
        for (Book book : bookRepository.findAll()){
            List<Review> reviews = reviewRepository.findReviewsByBook(book);
            double average = reviews.stream().mapToDouble(Review::getScore).average().orElseGet(() -> 0.0);

            if (average > max){
                max = average;
                bookForReturn = book;
            }
        }

        finalMap.put(bookForReturn, max);

        return finalMap;
    }
}

package mk.ukim.finki.wp.labofficial.web.controller;

import mk.ukim.finki.wp.labofficial.model.Book;
import mk.ukim.finki.wp.labofficial.model.Review;
import mk.ukim.finki.wp.labofficial.model.exceptions.BookNotFoundException;
import mk.ukim.finki.wp.labofficial.service.BookService;
import mk.ukim.finki.wp.labofficial.service.ReviewService;
import org.h2.engine.Mode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;

    public ReviewController(ReviewService reviewService, BookService bookService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
    }

    @GetMapping("/add-review/{bookId}")
    public String addReview(@PathVariable Long bookId, Model model){
        Book book = bookService.findBookById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
        model.addAttribute("book", book);
        return "add-review";
    }

    @PostMapping("/save")
    public String saveReview(@RequestParam Long bookId,
                             @RequestParam Integer score,
                             @RequestParam String description,
                             @RequestParam LocalDateTime date, Model model){
        reviewService.addReviewToBook(bookId, score, description, date);
        model.addAttribute("reviews", reviewService.findReviewsByBook(bookId));
        model.addAttribute("book", bookService.findBookById(bookId).orElseThrow());
        return "review-details";
    }

    @GetMapping("/getReviewPage/{bookId}")
    public String getReviewPage(@PathVariable Long bookId, Model model){
        model.addAttribute("reviews", reviewService.findReviewsByBook(bookId));
        model.addAttribute("book", bookService.findBookById(bookId).orElseThrow());
        return "review-details";
    }

    @GetMapping("/search")
    public String getReviews(@RequestParam(value = "from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                             @RequestParam(value = "to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                             @RequestParam(value="bookId") Long bookId,
                             Model model) {
        List<Review> results = reviewService.reviewsBetweenTimestamp(from, to);
        Book book = bookService.findBookById(bookId).orElseThrow();
        model.addAttribute("search", true);
        model.addAttribute("searchReviews", results);
        model.addAttribute("book", book);
        return "review-details";
    }
}

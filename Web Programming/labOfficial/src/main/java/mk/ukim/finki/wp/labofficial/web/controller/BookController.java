package mk.ukim.finki.wp.labofficial.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.labofficial.model.Book;
import mk.ukim.finki.wp.labofficial.service.BookService;
import mk.ukim.finki.wp.labofficial.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookController(BookService bookService, BookStoreService bookStoreService) {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBookPage(@RequestParam(required = false) String error, Model model){
        if (error != null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("books", bookService.listBooks());
        return "listBooks";
    }

    @PostMapping
    public String selectBook(HttpServletRequest req, @RequestParam(value = "selectedBook", required = false) String isbn, HttpSession session, Model model){
        String searchText = req.getParameter("searchText");

        if (searchText != null){
            model.addAttribute("books", bookService.searchBook(searchText));
            return "listBooks";
        }

        Book book = bookService.findBookByIsbn(isbn).get();
        session.setAttribute("bookSelect", book);
        return "redirect:/author";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable long id){
        bookService.deleteById(id);

        return "redirect:/books";
    }

    @GetMapping("/add-form")
    public String addBook(Model model){
        model.addAttribute("bookStores", bookStoreService.findAll());

        return "addBook";
    }

    @GetMapping("/edit-form/{id}")
    public String editBook(@PathVariable Long id, Model model){
        if (bookService.findBookById(id).isPresent()){
            Book book = bookService.findBookById(id).get();
            model.addAttribute("bookStores", bookStoreService.findAll());
            model.addAttribute("book", book);
            return "addBook";
        }

        return "redirect:/books?error=BookNotFound";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String isbn,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int year,
                           @RequestParam Long bookStore){
        bookService.saveOrUpdate(isbn, title, genre, year, bookStore);

        return "redirect:/books";
    }


}

package mk.ukim.finki.wp.labofficial.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.labofficial.model.Author;
import mk.ukim.finki.wp.labofficial.model.Book;
import mk.ukim.finki.wp.labofficial.service.AuthorService;
import mk.ukim.finki.wp.labofficial.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getAuthorPage(HttpSession session, Model model){
        model.addAttribute("authors", authorService.listAuthors());
        model.addAttribute("bookSelect", session.getAttribute("bookSelect"));
        return "/authorList";
    }

    @PostMapping
    public String selectAuthor(HttpSession session, @RequestParam("selectedAuthor") long id){
        Book book = (Book) session.getAttribute("bookSelect");
        bookService.addAuthorToBook(id, book.getId());
        session.setAttribute("authorSelect", authorService.findById(id).get());

        return "redirect:/bookDetails";
    }
}

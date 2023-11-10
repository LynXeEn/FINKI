package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "book-list", urlPatterns = "/listBooks")
public class BookListServlet extends HttpServlet {
    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookListServlet(BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("books", bookService.listBooks());

        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchText = req.getParameter("search");

        if (searchText != null){
            IWebExchange webExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
                    .buildExchange(req,resp);
            WebContext context = new WebContext(webExchange);
            context.setVariable("books", bookService.findBySearch(searchText));
            springTemplateEngine.process("listBooks.html", context, resp.getWriter());
        }

        String isbn = req.getParameter("selectedBook");
        Book book = bookService.findBookByIsbn(isbn);
        req.getSession().setAttribute("bookSelect", book);
        resp.sendRedirect("/author");

//        Long authorId = (Long) req.getSession().getAttribute("selectedAuthor");
//
//        bookService.addAuthorToBook(authorId, isbn);


    }
}

package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.service.AuthorService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "Author Servlet", urlPatterns = "/author")
public class AuthorServlet extends HttpServlet {
    private final AuthorService authorService;
    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public AuthorServlet(AuthorService authorService, BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(req.getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        context.setVariable("authors", authorService.listAuthors());

        springTemplateEngine.process("authorList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String authorId = req.getParameter("selectedAuthor");
        Author author = authorService.findById(Long.parseLong(authorId));

        // TODO tuka mora da se popravi, treba da se razmisli kaj mozhe da se dodade  avtorot na knigata, dali tuka dali vo posledniot servlet
        //bookService.addAuthorToBook(Long.parseLong(authorId), (String) req.getSession().getAttribute("selectedBook"));

        req.getSession().setAttribute("authorSelect", author);
        resp.sendRedirect("/bookDetails");
    }
}

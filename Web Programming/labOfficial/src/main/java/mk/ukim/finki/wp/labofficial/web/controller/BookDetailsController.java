package mk.ukim.finki.wp.labofficial.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookDetails")
public class BookDetailsController {

    @GetMapping
    public String getBookDetailsPage(){
        return "bookDetails";
    }

}

package am.itspace.overnight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserBookController {

    @GetMapping("user-book")
    public String userBook(){
        return "user-book";
    }
}

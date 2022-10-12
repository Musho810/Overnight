package am.itspace.overnight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommentsController {

    @GetMapping("comments")
    public String comments(){
        return "comments";
    }
}

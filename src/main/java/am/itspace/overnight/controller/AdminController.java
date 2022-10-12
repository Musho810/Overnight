package am.itspace.overnight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/adminPage")
    public String adminpage() {
        return "adminPage";
    }

    @GetMapping("/user")
    public String users(){
        return "adminPageUser";
    }

    @GetMapping("attribute")
    public String adminPageAttribute() {
        return "/adminPageAttribute";
    }
}

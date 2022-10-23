package am.itspace.overnight.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
        @GetMapping("/adminPage")
    public String adminpage() {
        return "admin/adminPage";
    }

    @GetMapping("/user")
    public String users(){
        return "admin/adminPageUser";
    }

    @GetMapping("/attributes")
    public String adminPageAttribute() {
        return "admin/adminPageAttribute";
    }


}

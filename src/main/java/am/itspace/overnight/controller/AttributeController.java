package am.itspace.overnight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AttributeController {

    @GetMapping("attribute")
    public String attribute(){
        return "attribute";
    }
}

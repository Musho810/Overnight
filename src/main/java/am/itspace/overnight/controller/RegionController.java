package am.itspace.overnight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegionController {

    @GetMapping("region")
    public String region(){
        return "region";
    }
}

package am.itspace.overnight.controller;

import am.itspace.overnight.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserBookController {

    private final RegionService regionService;

    @GetMapping("/regions")
    public String userBook() {
        return "searchResult";
    }

}
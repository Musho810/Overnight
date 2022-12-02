package am.itspace.overnight.controller;

import am.itspace.overnight.entity.Region;
import am.itspace.overnight.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final RegionService regionService;
    @GetMapping("/")
    public String mainPage(ModelMap modelMap) {
        List<Region> regions = regionService.findAll();
        modelMap.addAttribute("regions" , regions);
        return "index";
    }

}
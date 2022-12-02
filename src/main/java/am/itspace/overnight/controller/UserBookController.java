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
public class UserBookController {

    private final RegionService regionService;

    @GetMapping("/regions")
    public String userBook(){
        return "searchResult";
    }


//    @GetMapping("/index")
//    public String searchRegion(ModelMap modelMap) {
//        List<Region> regionAll = regionService.getAll();
//        modelMap.addAttribute("regions", regionAll);
//        return "index";
//    }
}
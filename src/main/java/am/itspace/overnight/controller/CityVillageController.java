package am.itspace.overnight.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CityVillageController {

    @GetMapping("city-village")
    public String cityVillage() {
        return "city-village";
    }
}

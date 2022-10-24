package am.itspace.overnight.controller;
import am.itspace.overnight.entity.CityVillage;
import am.itspace.overnight.entity.Region;
import am.itspace.overnight.service.CityVillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Optional;
@Controller
@RequiredArgsConstructor
public class CityVillageController {
    private final CityVillageService cityVillageService;
    @PostMapping("/cityvillage/add")
    public String addCityvillage(@ModelAttribute CityVillage cityVillage) {
        cityVillageService.addCityVillage(cityVillage);
        return "redirect:/cityvillage";
    }
    @GetMapping("/cityvillage")
    public String getAllCityvillage(ModelMap modelMap) {
        List<CityVillage> cityVillages = cityVillageService.getAll();
        modelMap.addAttribute("cityvillages", cityVillages);
        return "cityvillage";
    }
    @GetMapping("/cityvillage/id")
    public String getCityvillageById(@RequestParam ("cityvillageId") int cityvillageId, ModelMap modelMap) {
        Optional<CityVillage> cityvillage= cityVillageService.getById(cityvillageId);
        modelMap.addAttribute("cityvillage", cityvillage);
        return "cityvillage";
    }
    @PostMapping("/cityvillage/update")
    public String updateCityvillage (@RequestParam("cityvillageId") int cityvillageId,
                                @RequestParam("cityvillageName") String cityvillageName,
                                @RequestParam("regionId") Region region){
        cityVillageService.updateCityVillage(cityvillageId,cityvillageName,region);
        return "redirect:/cityvillage";
    }
    @GetMapping("/cityvillage/delete")
    public String deleteCityvillage(@RequestParam("cityvillageId") int cityvillageId) {
        cityVillageService.deleteById(cityvillageId);
        return "redirect:/cityvillage";
    }
}

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
    @PostMapping("/cityVilage/add")
    public String addCityVilage(@ModelAttribute CityVillage cityVillage) {
        cityVillageService.addCityVilage(cityVillage);
        return "redirect:/cityVilagies";
    }
    @GetMapping("/cityVilage")
    public String getAllCityVilage(ModelMap modelMap) {
        List<CityVillage> cityVillages = cityVillageService.getAll();
        modelMap.addAttribute("cityVilages", cityVillages);
        return "/cityVilagies";
    }
    @GetMapping("/cityVilage")
    public String getCityVilageById(@RequestParam int cityVilageId, ModelMap modelMap) {
        Optional<CityVillage> cityVilage= cityVillageService.getById(cityVilageId);
        modelMap.addAttribute("cityVilage", cityVilage);
        return "/cityVilagies";
    }
    @PostMapping("/cityVilage/update")
    public String updateCityVilage (@RequestParam("cityVilageId") int cityVilageId,
                                @RequestParam("cityVilageName") String cityVilageName,
                                @RequestParam("regionId") Region region){
        cityVillageService.updateCityVilage(cityVilageId,cityVilageName,region);
        return "redirect:/cityVilagies";
    }
    @GetMapping("/cityVilage/delete")
    public String deleteCityVilage(@RequestParam("cityVilageId") int cityVilageId) {
        cityVillageService.deleteById(cityVilageId);
        return "redirect:/cityVilagies";
    }
}
